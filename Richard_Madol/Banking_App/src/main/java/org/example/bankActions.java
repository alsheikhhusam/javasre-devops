package org.example;

public class bankActions {

    int balance = 50;


    void deposit(String name, int deposit) {
        System.out.println(name + " deposited " + deposit);
        balance = balance + deposit;
        System.out.println("Balance after deposit: "
                + balance);
    }

    void withdraw(String name, int withdrawal) {
        if (balance >= withdrawal) {
            System.out.println(name + " withdrew "
                    + withdrawal);

            balance = balance - withdrawal;
            System.out.println("Balance after withdrawal: "
                    + balance);
        }
    }
    public static void main (String args[]) {
        bankActions actions = new bankActions();

        actions.deposit("Moe", 30);
        actions.withdraw("Ashley", 74);
    }
}


