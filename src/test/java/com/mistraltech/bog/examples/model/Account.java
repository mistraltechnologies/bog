package com.mistraltech.bog.examples.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Account {
    private Person holder;

    private int balance;

    private Set<Person> associateHoldersSet = new HashSet<Person>();

    private List<Person> associateHoldersList = new ArrayList<Person>();

    private AccountManager accountManager;

    public Account(Person holder, int balance, AccountManager accountManager) {
        this.holder = holder;
        this.balance = balance;
        this.accountManager = accountManager;
    }

    public Person getHolder() {
        return holder;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isOverdrawn() {
        return balance < 0;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public Set<Person> getAssociateHoldersSet() {
        return associateHoldersSet;
    }

    public List<Person> getAssociateHoldersList() {
        return associateHoldersList;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void addAssociateHolder(Person person) {
        associateHoldersSet.add(person);
        associateHoldersList.add(person);
    }
}