package com.mistraltech.bog.examples.defaults;

import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.defaults.builder.AbstractPersonBuilder.PersonBuilderType;
import static com.mistraltech.bog.examples.defaults.builder.AbstractPersonBuilder.PersonBuilderType.aPerson;
import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static com.mistraltech.bog.examples.model.Gender.Female;
import static com.mistraltech.bog.examples.model.Gender.Male;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {
    @Test
    public void canCreatePersonWithDefaults() {
        PersonBuilderType bobBuilder = aPerson().withName("Bob");

        Person bob = bobBuilder.build();

        assertThat(bob.getName(), is("Bob"));
        assertThat(bob.getGender(), is(Male));
    }

    @Test
    public void canCreatePersonWithUpdatedDefaults() {
        PersonBuilderType bobBuilder = aPerson().withName("Bob");
        // TODO set default gender to female

        Person bob = bobBuilder.build();

        assertThat(bob, is(aPersonThat()
                .hasName("Bob")
                .hasGender(Female)));
    }

    @Test
    public void canCreatePersonWithAdaptiveDefaults() {
        PersonBuilderType bobBuilder = aPerson()
                .withName("Bob")
                .withSpouse(aPerson()
                        .withName("Mary"));

        Person bob = bobBuilder.build();

        assertThat(bob, is(aPersonThat()
                .hasName("Bob")
                .hasGender(Male)
                .hasSpouse(aPersonThat()
                        .hasName("Mary")
                        .hasGender(Female))));
    }
}
