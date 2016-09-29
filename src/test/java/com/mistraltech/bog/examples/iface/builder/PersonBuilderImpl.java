package com.mistraltech.bog.examples.iface.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

public class PersonBuilderImpl extends AbstractBuilder<Person> implements PersonBuilder {
    private final ValueContainer<String> name = ValueContainer.valueContainer(getDefaultName());

    private final ValueContainer<Person> spouse = ValueContainer.valueContainer();

    private final ValueContainer<Gender> gender = ValueContainer.valueContainer(getDefaultGender());

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
    public Gender getGender() {
        return gender.get();
    }

    @Override
    protected Person construct() {
        return new Person(name.get(), gender.get());
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.get());
    }
}
