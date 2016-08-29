package com.mistraltech.bog.examples.cyclicdefaults.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.picker.EnumValuePicker;
import com.mistraltech.bog.core.propertybuilder.ValueContainer;
import com.mistraltech.bog.core.propertybuilder.ValueProvider;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

import static com.mistraltech.bog.core.picker.IntegerValuePicker.integerValuePicker;
import static com.mistraltech.bog.core.propertybuilder.ValueContainer.valueContainer;

public final class PersonBuilder extends AbstractBuilder<Person> {
    private ValueContainer<Person> spouse = valueContainer();

    private ValueContainer<Gender> gender = ValueContainer.valueContainer(() ->
            spouse.preview() == null ?
                    EnumValuePicker.enumPicker(Gender.class).pick() : (spouse.preview().getGender() == Gender.Male ?
                    Gender.Female : Gender.Male));

    private ValueContainer<Integer> age = ValueContainer.valueContainer(integerValuePicker(18, 40));

    private ValueContainer<String> name = ValueContainer.valueContainer(() -> gender.preview() == Gender.Male ? "Bill" : "Bob");

    private PersonBuilder() {
    }

    private PersonBuilder(Person template) {
        withName(template.getName());
        withSpouse(template.getSpouse());
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }

    public static PersonBuilder aPersonFrom(Person template) {
        return new PersonBuilder(template);
    }

    public PersonBuilder withName(String name) {
        this.name.set(name);
        return this;
    }

    public PersonBuilder withSpouse(Person spouse) {
        this.spouse.set(spouse);
        return this;
    }

    public PersonBuilder withSpouse(Builder<? extends Person> spouseBuilder) {
        this.spouse.set(spouseBuilder);
        return this;
    }

    public PersonBuilder withGender(Gender gender) {
        this.gender.set(gender);
        return this;
    }

    protected ValueProvider<Integer> age() {
        return age;
    }

    protected ValueProvider<String> name() {
        return name;
    }

    protected ValueProvider<Person> spouse() {
        return spouse;
    }

    @Override
    protected Person construct() {
        return new Person(name.take(), gender.take());
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.take());
        instance.setAge(age.take());
    }
}