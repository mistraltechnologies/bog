package com.mistraltech.bog.examples.defaultpicker;

import com.mistraltech.bog.examples.defaultpicker.builder.PersonBuilder;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.mistraltech.bog.core.picker.IteratingValuePicker.iteratingValuePicker;
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

    @Test
    public void canUseIteratorForDefaults() {
        PersonBuilder builder = aPerson().setDefaultAge(iteratingValuePicker(new Fibonacci(10)));

        assertThat(builder.build(), is(aPersonThat().hasAge(1)));
        assertThat(builder.build(), is(aPersonThat().hasAge(1)));
        assertThat(builder.build(), is(aPersonThat().hasAge(2)));
        assertThat(builder.build(), is(aPersonThat().hasAge(3)));
        assertThat(builder.build(), is(aPersonThat().hasAge(5)));
        assertThat(builder.build(), is(aPersonThat().hasAge(8)));
        assertThat(builder.build(), is(aPersonThat().hasAge(1)));
    }


    private static class Fibonacci implements Iterable<Integer> {

        private final int max;

        Fibonacci(int max) {
            this.max = max;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int previous = 0;

                private int next = 1;

                @Override
                public boolean hasNext() {
                    return next <= max;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }

                    int savedNext = next;

                    next += previous;
                    previous = savedNext;

                    return savedNext;
                }
            };
        }
    }
}
