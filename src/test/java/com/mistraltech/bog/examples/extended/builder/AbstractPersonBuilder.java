package com.mistraltech.bog.examples.extended.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

public abstract class AbstractPersonBuilder<R extends AbstractPersonBuilder, T extends Person> extends AbstractBuilder<T> {
    private ValueContainer<String> name = new ValueContainer<>("Bob");

    private ValueContainer<Person> spouse = new ValueContainer<>();

    private ValueContainer<Gender> gender = new ValueContainer<>(Gender.Male);

    protected AbstractPersonBuilder() {
    }

    protected AbstractPersonBuilder(T template) {
        withName(template.getName());
        withSpouse(template.getSpouse());
    }

    @SuppressWarnings("unchecked")
    protected R self() {
        return (R) this;
    }

    public R withName(String name) {
        this.name.set(name);
        return self();
    }

    public R withSpouse(Person spouse) {
        this.spouse.set(spouse);
        return self();
    }

    public R withSpouse(Builder<? extends Person> spouseBuilder) {
        this.spouse.set(spouseBuilder);
        return self();
    }

    public R withGender(Gender gender) {
        this.gender.set(gender);
        return self();
    }

    protected BuilderProperty<String> getName() {
        return name;
    }

    protected BuilderProperty<Person> getSpouse() {
        return spouse;
    }

    protected BuilderProperty<Gender> getGender() {
        return gender;
    }

    @Override
    protected void assign(T instance) {
        instance.setSpouse(spouse.value());
    }

    public static final class PersonBuilder extends AbstractPersonBuilder<PersonBuilder, Person> {
        public PersonBuilder() {
        }

        public PersonBuilder(Person template) {
            super(template);
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public static PersonBuilder aPersonFrom(Person template) {
            return new PersonBuilder(template);
        }

        protected Person construct() {
            return new Person(getName().value(),
                    getGender().value());
        }
    }
}