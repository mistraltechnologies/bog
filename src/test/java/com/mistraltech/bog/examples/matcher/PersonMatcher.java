package com.mistraltech.bog.examples.matcher;

import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Person.class)
public final class PersonMatcher extends CompositePropertyMatcher<Person> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a Person";
    private final PropertyMatcher<Person> spouseMatcher = new ReflectingPropertyMatcher<Person>("spouse", this);
    private final PropertyMatcher<String> nameMatcher = new ReflectingPropertyMatcher<String>("name", this);
    private final PropertyMatcher<Gender> genderMatcher = new ReflectingPropertyMatcher<Gender>("gender", this);

    private PersonMatcher(final String matchedObjectDescription, final Person template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasSpouse(template.getSpouse());
            hasName(template.getName());
            hasGender(template.getGender());
        }
    }

    public static PersonMatcher aPersonThat() {
        return new PersonMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static PersonMatcher aPersonLike(final Person template) {
        return new PersonMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public PersonMatcher hasSpouse(final Person spouse) {
        return hasSpouse(equalTo(spouse));
    }

    public PersonMatcher hasSpouse(final Matcher<? super Person> spouseMatcher) {
        this.spouseMatcher.setMatcher(spouseMatcher);
        return this;
    }

    public PersonMatcher hasName(final String name) {
        return hasName(equalTo(name));
    }

    public PersonMatcher hasName(final Matcher<? super String> nameMatcher) {
        this.nameMatcher.setMatcher(nameMatcher);
        return this;
    }

    public PersonMatcher hasGender(final Gender gender) {
        return hasGender(equalTo(gender));
    }

    public PersonMatcher hasGender(final Matcher<? super Gender> genderMatcher) {
        this.genderMatcher.setMatcher(genderMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Person item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
