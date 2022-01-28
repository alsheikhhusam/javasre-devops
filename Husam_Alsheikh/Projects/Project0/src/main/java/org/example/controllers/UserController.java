package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.example.dto.AccountDTO;
import org.example.dto.TransferDTO;
import org.example.models.Roles;
import org.example.models.User;
import org.example.services.AccountService;
import org.example.services.UserService;
import org.jetbrains.annotations.NotNull;

public class UserController implements CrudHandler {
    private AccountService accountService;
    private UserService userService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    public UserController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void getAll(@NotNull Context context) {  //  Get Transaction History

    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {   //  View Balance By Account Number
        int id = Integer.parseInt(s);   //  Account Number

        //  Logged in user
        User logger = context.cookieStore("principal");

        //  If no logged-in users throw error
        if(logger == null){
            throw new ForbiddenResponse("No user found - User not authorized");
        }

        AccountDTO accountDTO = accountService.getAccount(id);  //  Get Bank Account based on Account ID

        //  Check to see if account belongs to the logged-in user
        if(!logger.getRoles().contains(Roles.EMPLOYEE)){
            if(accountDTO.getUserid() != logger.getId() || !accountDTO.getUsername().equals(logger.getUsername())){
                throw new ForbiddenResponse("User not authorized to view account balance");
            }
        }

        context.header("Location", "http://localhost:4200/user/viewBalance/" + id);
        context.status(201);
        context.result(String.valueOf(accountDTO.getBalance()));
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {   //  Transfer, Deposit, and Withdraw
        int id = Integer.parseInt(s);   //  Account Number

        //  Logged in user
        User logger = context.cookieStore("principal");

        //  Implementation for transfer
        if(context.path().equals("/user/transfer/accountNumber/" + id)){
            if(!logger.getRoles().contains(Roles.EMPLOYEE)){
                //  Get user from account number
                User accountUser = userService.getUserByUsername(accountService.getAccount(id).getUsername());

                if(accountUser.getId() != logger.getId() || !accountUser.getUsername().equals(logger.getUsername())){
                    throw new ForbiddenResponse("User not authorized to transfer balance");
                }
            }

            TransferDTO transferDTO = context.bodyAsClass(TransferDTO.class);

            //  Get the user that is transferring FROM
            User fromUser = userService.getUserByUsername(transferDTO.getUsername());

            //  If user id, password, account all match the user with that username; Only then allow balance transfer
            if(fromUser.getId() == transferDTO.getUserid() && fromUser.getPassword().equals(transferDTO.getPassword()) && fromUser.getAccountsId().contains(transferDTO.getAccountNum())){
                //  Transfer Balance
                AccountDTO receiverAccount = accountService.transfer(accountService.getAccount(id), accountService.getAccount(transferDTO.getAccountNum()), transferDTO.getTransferAmount());

                //  Print receiver's new balance and account details
                context.json(receiverAccount);
                return;
            }

            throw new ForbiddenResponse("Sending User account information is incorrect");
        }

        //  Implementation for deposit and withdraw

        int amount = Integer.parseInt(context.body());

        //  If no logged -in users throw error
        if(logger == null){
            throw new ForbiddenResponse("No user found - User not authorized");
        }

        AccountDTO accountDTO = accountService.getAccount(id);  //  Get Bank Account

        //  Check to see if account belongs to the logged-in user
        if(!logger.getRoles().contains(Roles.EMPLOYEE)){
            if(accountDTO.getUserid() != logger.getId() || !accountDTO.getUsername().equals(logger.getUsername())){
                throw new ForbiddenResponse("User not authorized to deposit");
            }
        }

        //  Navigate based on path
        if(context.path().equals("/user/deposit/accountNumber/" + id)){   //  If deposit path
            accountDTO.setBalance(accountDTO.getBalance() + amount);
        }
        else if(context.path().equals("/user/withdraw/accountNumber/" + id)){ //  If withdraw path
            if(accountDTO.getBalance() < amount){
                throw new ArithmeticException("Amount to be withdrawn is greater than balance");
            }

            accountDTO.setBalance(accountDTO.getBalance() - amount);
        }

        accountService.updateBalance(accountDTO);

        context.header("Location", "http://localhost:4200/user/deposit/" + id);
        context.status(201);
        context.json(accountService.getAccount(id));
    }

    @Override
    public void create(@NotNull Context context) {
        throw new ForbiddenResponse("Function has no implementation");
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("Function has no implementation");
    }
}
