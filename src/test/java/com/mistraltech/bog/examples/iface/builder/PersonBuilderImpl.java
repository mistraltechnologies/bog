package com.mistraltech.bog.examples.iface.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

public class PersonBuilderImpl extends AbstractBuilder<Person> implements PersonBuilder {
    private final ValueContainer<String> name = new ValueContainer<>(getDefaultName());

    private final ValueContainer<Person> spouse = new ValueContainer<>();

    private final ValueContainer<Gender> gender = new ValueContainer<>(getDefaultGender());

    PersonBuilderImpl() {
    }

    public PersonBuilder from(Person template) {
        withName(template.getName());
        withSpouse(template.getSpouse());
        withGender(template.getGender());
        return this;
    }

    @Override
    public PersonBuilder withName(String name) {
        this.name.set(name);
        return this;
    }

    @Override
    public PersonBuilder withSpouse(Person spouse) {
        this.spouse.set(spouse);
        return this;
    }

    @Override
    public PersonBuilder withSpouse(Builder<? extends Person> spouseBuilder) {
        this.spouse.set(spouseBuilder);
        return this;
    }

    @Override
    public PersonBuilder withGender(Gender gender) {
        this.gender.set(gender);
        return this;
    }

    @Override
    public BuilderProperty<Gender> getGender() {
        return gender;
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
