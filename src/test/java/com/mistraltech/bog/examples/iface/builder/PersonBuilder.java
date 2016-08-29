package com.mistraltech.bog.examples.iface.builder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.picker.ValuePicker;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;

public interface PersonBuilder extends TwoPhaseBuilder<Person> {

    // TODO: return an instance of the PersonBuilder class generated from this interface
    static PersonBuilder aPerson() {
        return new PersonBuilderImpl();
    }

    // TODO: return an instance of the PersonBuilder class generated from this interface and populated from template
    static PersonBuilder aPersonFrom(Person template) {
        return new PersonBuilderImpl(template);
    }

    PersonBuilder withName(String name);

    PersonBuilder withSpouse(Person spouse);

    PersonBuilder withSpouse(Builder<? extends Person> spouseBuilder);

    PersonBuilder withGender(Gender gender);

    default ValuePicker<String> getDefaultName() {
        return singleValuePicker("Bob");
    }

    default ValuePicker<Gender> getDefaultGender() {
        return singleValuePicker(Gender.Male);
    }
}
