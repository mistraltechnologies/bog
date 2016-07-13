package com.mistraltech.bog.examples.cyclicdefaults;

import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.cyclicdefaults.builder.PersonBuilder.aPerson;
import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {
    @Test
    public void canCreatePerson() {
        Person x = aPerson().build();
        // TODO: x is either a person with one set of properties or the other
        assertThat(x, is(aPersonThat().hasName(equalTo("Bob"))));
    }
}
