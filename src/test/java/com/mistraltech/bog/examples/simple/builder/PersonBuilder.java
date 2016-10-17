package com.mistraltech.bog.examples.simple.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

public final class PersonBuilder extends AbstractBuilder<Person> {
    private ValueContainer<String> name = new ValueContainer<>("Bob");

    private ValueContainer<Person> spouse = new ValueContainer<>();

    private ValueContainer<Gender> gender = new ValueContainer<>(Gender.Male);

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

    @Override
    protected Person construct() {
        return new Person(name.value(), gender.value());
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.value());
    }
}