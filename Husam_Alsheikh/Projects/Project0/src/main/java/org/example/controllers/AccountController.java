package org.example.controllers;

import io.javalin.http.ForbiddenResponse;
import io.javalin.http.Handler;
import org.example.dto.AccountDTO;
import org.example.dto.TransactionDTO;
import org.example.dto.TransferDTO;
import org.example.models.Roles;
import org.example.models.User;
import org.example.services.AccountService;
import org.example.services.UserService;

import java.util.List;

public class AccountController {
    private AccountService accountService;
    private UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    public Handler getBalance = (context) -> {
        int acctId = context.pathParamAsClass("acctId", Integer.class).get();
        AccountDTO accountDTO = accountService.getAccount(acctId);  //  Get Bank Account based on Account ID

        //  Get logged-in user
        User logger = context.cookieStore("principal");

        //  If no logged-in users
        if(logger == null){
            throw new ForbiddenResponse("No user found - User not authorized");
        }

        //  Check to see if account belongs to the logged-in user or a
        if(!logger.getRoles().contains(Roles.ROLE_ADMIN)){
            if(accountDTO.getUserid() != logger.getId() || !accountDTO.getUsername().equals(logger.getUsername())){
                throw new ForbiddenResponse("User not authorized to view account balance");
            }
        }

        //  Add Transaction
        TransactionDTO transactionDTO = new TransactionDTO(accountDTO.getUserid(), accountDTO.getUsername(), accountDTO.getAccountNum(), "Balance View");
        userService.updateUserTransaction(transactionDTO);

        context.result(String.valueOf(accountDTO.getBalance()));
    };

    public Handler deposit = (context) ->{
        int amount = Integer.parseInt(context.body());
        int acctId = context.pathParamAsClass("acctId", Integer.class).get();
        AccountDTO accountDTO = accountService.getAccount(acctId);

        //  Get logged-in user
        User loggedUser = context.cookieStore("principal");

        //  If no logged-in user throw error
        if(loggedUser == null){
            throw new ForbiddenResponse("User must log-in - User not authorized");
        }

        //  Check if Employee or account belongs to the logged-in user
        if(!loggedUser.getRoles().contains(Roles.ROLE_ADMIN)){
            if(accountDTO.getUserid() != loggedUser.getId() || !accountDTO.getUsername().equals(loggedUser.getUsername())){
                throw new ForbiddenResponse("User not authorized to deposit");
            }
        }

        //  Update Balance
        accountDTO.setBalance(accountDTO.getBalance() + amount);
        accountService.updateBalance(accountDTO);

        //  Add Transaction
        TransactionDTO transactionDTO = new TransactionDTO(accountDTO.getUserid(), accountDTO.getUsername(), acctId, amount, "Balance Deposit");
        userService.updateUserTransaction(transactionDTO);

        context.json(accountDTO);
    };

    public Handler withdraw = (context) -> {
        int amount = Integer.parseInt(context.body());
        int acctId = context.pathParamAsClass("acctId", Integer.class).get();
        AccountDTO accountDTO = accountService.getAccount(acctId);

        //  Get logged-in user
        User loggedUser = context.cookieStore("principal");

        //  If no logged-in user throw error
        if(loggedUser == null){
            throw new ForbiddenResponse("User must log-in - User not authorized");
        }

        //  Check if Employee or account belongs to the logged-in user
        if(!loggedUser.getRoles().contains(Roles.ROLE_ADMIN)){
            if(accountDTO.getUserid() != loggedUser.getId() || !accountDTO.getUsername().equals(loggedUser.getUsername())){
                throw new ForbiddenResponse("User not authorized to withdraw");
            }
        }

        //  Update Balance
        accountDTO.setBalance(accountDTO.getBalance() - amount);
        accountService.updateBalance(accountDTO);

        //  Add Transaction
        TransactionDTO transactionDTO = new TransactionDTO(accountDTO.getUserid(), accountDTO.getUsername(), acctId, amount, "Balance Withdraw");
        userService.updateUserTransaction(transactionDTO);

        context.json(accountDTO);
    };

    public Handler transfer = (context) -> {
        int userid = context.pathParamAsClass("id", Integer.class).get();

        //  Logged in user
        User loggedUser = context.cookieStore("principal");

        //  If no logged-in user throw error
        if(loggedUser == null){
            throw new ForbiddenResponse("User must log-in - User not authorized");
        }

        //  Authenticate User - Make Sure user is employee or account owner
        if(!loggedUser.getRoles().contains(Roles.ROLE_ADMIN)){
            User accountUser = userService.getUserByID(userid);

            if(accountUser.getId() != loggedUser.getId() || !accountUser.getUsername().equals(loggedUser.getUsername())){
                throw new ForbiddenResponse("User not authorized to transfer balance");
            }
        }

        TransferDTO transferDTO = context.bodyAsClass(TransferDTO.class);

        //  Get the user that is SENDING Balance (fromUser) and the user that is RECEIVING Balance (toUser)
        User fromUser = userService.getUserByID(userid);
        User toUser = userService.getUserByID(transferDTO.getUserid());

        //  If user id, password, accountNum all match the user with that username; Only then allow balance transfer
        if(toUser.getId() == transferDTO.getUserid() && toUser.getPassword().equals(transferDTO.getPassword()) && toUser.getAccountsId().contains(transferDTO.getAccountNum())){
            //  Transfer Balance
            AccountDTO receiverAccount = accountService.transfer(accountService.getAccount(userid), accountService.getAccount(transferDTO.getAccountNum()), transferDTO.getTransferAmount());

            //  Add Transaction
            TransactionDTO transactionDTO = new TransactionDTO(fromUser.getId(), fromUser.getUsername(), userid, transferDTO.getTransferAmount(), "Balance Transfer");
            userService.updateUserTransaction(transactionDTO);

            //  Print receiver's new balance and account details
            context.json(receiverAccount);
            return;
        }

        throw new ForbiddenResponse("Sending User account information is incorrect");
    };

    public Handler getTransactionHistory = (context) -> {
        int id = context.pathParamAsClass("id", Integer.class).get();
        AccountDTO accountDTO = accountService.getAccount(id);  //  Get Bank Account based on Account ID

        //  Get logged-in user
        User logger = context.cookieStore("principal");

        //  If no logged-in users
        if(logger == null){
            throw new ForbiddenResponse("No user found - User not authorized");
        }

        //  Check to see if account belongs to the logged-in user or a
        if(!logger.getRoles().contains(Roles.ROLE_ADMIN)){
            if(accountDTO.getUserid() != logger.getId() || !accountDTO.getUsername().equals(logger.getUsername())){
                throw new ForbiddenResponse("User not authorized to view account balance");
            }
        }

        //  Get transaction list and output
        List<TransactionDTO> transactions = userService.getUserByID(id).getTransactions();
        if(transactions.isEmpty()){
            context.result("No transactions to show!");
            return;
        }

        context.json(transactions);
    };
}
