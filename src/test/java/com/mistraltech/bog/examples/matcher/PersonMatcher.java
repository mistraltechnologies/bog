package com.mistraltech.bog.examples.matcher;

import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import static com.mistraltech.smog.proxy.javassist.JavassistMatcherGenerator.matcherOf;

@Matches(value = com.mistraltech.bog.examples.model.Person.class, description = "a Person")
public interface PersonMatcher extends Matcher<Person> {
    static PersonMatcher aPersonThat() {
        return matcherOf(PersonMatcher.class);
    }

    static PersonMatcher aPersonLike(final Person template) {
        return matcherOf(PersonMatcher.class).like(template);
    }

    PersonMatcher like(final Person template);

    PersonMatcher hasGender(final Gender gender);

    PersonMatcher hasGender(final Matcher<? super Gender> genderMatcher);

    PersonMatcher hasName(final String name);

    PersonMatcher hasName(final Matcher<? super String> nameMatcher);

    PersonMatcher hasSpouse(final Person spouse);

    PersonMatcher hasSpouse(final Matcher<? super Person> spouseMatcher);

    PersonMatcher hasAge(final int age);

    PersonMatcher hasAge(final Matcher<? super Integer> ageMatcher);
}
