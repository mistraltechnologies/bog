package com.mistraltech.bog.examples.simple;

import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import com.mistraltech.bog.examples.simple.builder.PersonBuilder;
import org.junit.Test;

import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static com.mistraltech.bog.examples.simple.builder.PersonBuilder.aPerson;
import static com.mistraltech.bog.examples.simple.builder.PersonBuilder.aPersonFrom;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {
    @Test
    public void canCreatePerson() {
        Person bob = aPerson().withName("Bob").build();
        assertThat(bob, is(aPersonThat().hasName(equalTo("Bob"))));
    }

    @Test
    public void canCreateHusbandAndWife() {
        PersonBuilder maryBuilder = aPerson().withName("Mary");
        Person bob = aPerson().withName("Bob").withSpouse(maryBuilder.create()).build();
        Person mary = maryBuilder.withSpouse(bob).update();

        assertThat(mary, is(aPersonThat().hasSpouse(sameInstance(bob))));
        assertThat(bob, is(aPersonThat().hasSpouse(sameInstance(mary))));
    }

    @Test
    public void canCreateFromPerson() {
        Person george = aPerson().withName("George").withGender(Gender.Male).build();
        Person georgina = aPersonFrom(george).withGender(Gender.Female).build();

        assertThat(georgina, is(aPersonThat().hasName("George").hasGender(Gender.Female)));
    }
}
