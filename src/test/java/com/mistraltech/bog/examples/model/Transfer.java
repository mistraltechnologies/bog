package com.mistraltech.bog.examples.model;


public class Transfer {
    private Account from;
    private Account to;
    private int amount;

    public Transfer(Account from, Account to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        from.withdraw(amount);
        to.deposit(amount);
    }

    public Account getFromAccount() { return from; }
    public Account getToAccount() { return to; }
    public int getAmount() { return amount; }
}