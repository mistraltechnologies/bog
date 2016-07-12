package com.mistraltech.bog.examples.extended.builder;

import com.mistraltech.bog.examples.model.AccountManager;

public class AccountManagerBuilder extends AbstractPersonBuilder<AccountManagerBuilder, AccountManager> {
    public AccountManagerBuilder() {
    }

    public AccountManagerBuilder(AccountManager template) {
        super(template);
    }

    public static AccountManagerBuilder anAccountManager() {
        return new AccountManagerBuilder();
    }

    public static AccountManagerBuilder anAccountManagerFrom(AccountManager template) {
        return new AccountManagerBuilder(template);
    }

    protected AccountManager construct() {
        return new AccountManager(getName().setDefault("Steve").get());
    }
}
