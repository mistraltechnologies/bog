package com.mistraltech.bog.examples.iface;

import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.iface.builder.PersonBuilder.aPerson;
import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {
    @Test
    public void canCreatePerson() {
        Person bob = aPerson().build();
        assertThat(bob, is(aPersonThat().hasGender(Gender.Female).hasName(equalTo("Bob"))));
    }

    @Test
    public void canCreateFromPerson() {
        Person george = aPerson().withName("George").withGender(Gender.Male).build();
        Person georgina = aPerson().from(george).withGender(Gender.Female).build();

        assertThat(georgina, is(aPersonThat().hasName("George").hasGender(Gender.Female)));
    }
}
