package com.mistraltech.bog.examples.extended;

import com.mistraltech.bog.examples.model.AccountManager;
import org.junit.Test;

import static com.mistraltech.bog.examples.extended.builder.AccountManagerBuilder.anAccountManager;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountManagerTest {
    @Test
    public void canCreateAccountManagerWithDefaultName() {
        AccountManager steve = anAccountManager().build();
        assertThat(steve.getName(), is(equalTo("Steve")));
    }

    @Test
    public void canCreateAccountManagerWithSpecifiedName() {
        AccountManager george = anAccountManager().withName("George").build();
        assertThat(george.getName(), is(equalTo("George")));
    }
}
