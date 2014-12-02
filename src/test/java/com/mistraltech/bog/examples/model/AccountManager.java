package com.mistraltech.bog.examples.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountManager extends Person {

    private List<Account> managedAccounts = new ArrayList<Account>();

    public AccountManager(String name) {
        super(name, Gender.Female);
    }

    public void manageAccount(Account account) {
        managedAccounts.add(account);
    }

    public List<Account> getManagedAccounts() {
        return Collections.unmodifiableList(managedAccounts);
    }
}
