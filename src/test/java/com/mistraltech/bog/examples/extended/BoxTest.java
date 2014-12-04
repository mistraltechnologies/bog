package com.mistraltech.bog.examples.extended;

import com.mistraltech.bog.examples.extended.builder.AbstractBoxBuilder;
import com.mistraltech.bog.examples.model.Box;
import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import static com.mistraltech.bog.examples.extended.builder.AbstractPersonBuilder.PersonBuilderType.aPerson;
import static com.mistraltech.bog.examples.matcher.PersonMatcher.aPersonThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoxTest {
    @Test
    public void testBoxOfInteger() {
        Box<Integer> integerBox = AbstractBoxBuilder.BoxBuilder.<Integer>aBox().withContents(25).build();
        assertThat(integerBox.getContents(), is(equalTo(25)));
    }

    @Test
    public void testBoxOfPerson() {
        Box<Person> personBox = AbstractBoxBuilder.BoxBuilder.<Person>aBox().withContents(aPerson().withName("Bob")).build();
        assertThat(personBox.getContents(), is(aPersonThat().hasName("Bob")));
    }
}
