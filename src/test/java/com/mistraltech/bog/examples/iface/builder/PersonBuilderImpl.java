package com.mistraltech.bog.examples.iface.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.propertybuilder.PropertyBuilder;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

public class PersonBuilderImpl extends AbstractBuilder<Person> implements PersonBuilder {
    private final PropertyBuilder<String> name = PropertyBuilder.propertyBuilder(getDefaultName());

    private final PropertyBuilder<Person> spouse = PropertyBuilder.propertyBuilder();

    private final PropertyBuilder<Gender> gender = PropertyBuilder.propertyBuilder(getDefaultGender());

    PersonBuilderImpl() {
    }

    PersonBuilderImpl(Person template) {
        withName(template.getName());
        withSpouse(template.getSpouse());
        withGender(template.getGender());
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
    protected Person construct() {
        return new Person(name.get(), gender.get());
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.get());
    }
}
