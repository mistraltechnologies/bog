package com.mistraltech.bog.examples.defaultpicker;

import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.defaultpicker.builder.PersonBuilder.aPerson;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;

public class PersonTest {
    @Test
    public void canCreatePerson() {
        Person bob = aPerson().build();
        System.out.println(bob);
        assertThat(bob.getGender(), is(anyOf(equalTo(Gender.Male), equalTo(Gender.Female))));
        assertThat(bob.getName(), is(anyOf(equalTo("Bob"), equalTo("Bill"))));
    }
}
