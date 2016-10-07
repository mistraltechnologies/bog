package com.mistraltech.bog.examples.defaultpicker;

import com.mistraltech.bog.examples.defaultpicker.builder.PersonBuilder;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.defaultpicker.builder.PersonBuilder.aPerson;
import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;

public class PersonTest {
    @Test
    public void canCreatePerson() {
        Person bob = aPerson().build();

        assertThat(bob, is(aPersonThat()
                .hasGender(anyOf(equalTo(Gender.Male), equalTo(Gender.Female)))
                .hasName(anyOf(equalTo("Bob"), equalTo("Bill")))));
    }

}
