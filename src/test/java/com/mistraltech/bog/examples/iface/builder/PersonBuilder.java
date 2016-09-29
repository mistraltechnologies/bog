package com.mistraltech.bog.examples.iface.builder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.ConstructorParameter;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;

public interface PersonBuilder extends TwoPhaseBuilder<Person> {

    static PersonBuilder aPerson() {
        return new PersonBuilderImpl();
    }

    PersonBuilder from(Person template);

    @ConstructorParameter(0)
    PersonBuilder withName(String name);

    @ConstructorParameter(1)
    PersonBuilder withSpouse(Person spouse);

    PersonBuilder withSpouse(Builder<? extends Person> spouseBuilder);

    PersonBuilder withGender(Gender gender);

    Gender getGender();

    // TODO: how do we go about specifying defaults for a builder generated at runtime?
    // Do we use default methods like below to return a default picker?
    // Do we instead write an abstract class and then generate a fully implemented subclass?
    // Do we use annotations to specify a value picker class for each with... method, but then how
    // do we describe complex inter-value relationships?

    default Supplier<String> getDefaultName() {
        return () -> getGender() == Gender.Male ? "Bill" : "Bob";
    }

    default Supplier<Gender> getDefaultGender() {
        return singleValuePicker(Gender.Female);
    }

}
