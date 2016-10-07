package com.mistraltech.bog.examples.dependentdefaults;

import com.mistraltech.bog.examples.dependentdefaults.builder.PersonBuilder;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.dependentdefaults.builder.PersonBuilder.aPerson;
import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {
    @Test
    public void canCreateWithInterdependentPropertyDefaults() {
        PersonBuilder xBuilder = aPerson();
        Person y = aPerson().withSpouse(xBuilder.create()).build();
        Person x = xBuilder.withSpouse(y).update();

        assertThat(x.getSpouse(), is(sameInstance(y)));
        assertThat(y.getSpouse(), is(sameInstance(x)));
        assertThat(x.getGender(), is(not(equalTo(y.getGender()))));
        assertThat(x, anyOf(
                aPersonThat().hasGender(Gender.Male).hasName("Bill"),
                aPersonThat().hasGender(Gender.Female).hasName("Bob")));
    }
}
