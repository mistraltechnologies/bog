package com.mistraltech.bog.examples.simple.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.PropertyBuilder;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

import static com.mistraltech.bog.core.PropertyBuilder.propertyBuilder;

public class PersonBuilder extends AbstractBuilder<Person> {
    private PropertyBuilder<String> name = propertyBuilder();
    private PropertyBuilder<Person> spouse = propertyBuilder();
    private PropertyBuilder<Gender> gender = propertyBuilder();

    protected PersonBuilder() {
    }

    protected PersonBuilder(Person template) {
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

    public PersonBuilder withGender(Builder<? extends Gender> genderBuilder) {
        this.gender.set(genderBuilder);
        return this;
    }

    @Override
    protected Person construct() {
        return new Person(name.getOrDefault("Bob"), gender.getOrDefault(Gender.Male));
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.getOrNull());
    }
}