package com.mistraltech.bog.examples.extended.builder;

import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.PropertyBuilder;

public abstract class AbstractPersonBuilder<R extends AbstractPersonBuilder, T extends Person> extends AbstractBuilder<T> {
    private PropertyBuilder<String> name = new PropertyBuilder<String>();
    private PropertyBuilder<Person> spouse = new PropertyBuilder<Person>();
    private PropertyBuilder<Gender> gender = new PropertyBuilder<Gender>();

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

    protected PropertyBuilder<String> getName() {
        return name;
    }

    protected PropertyBuilder<Person> getSpouse() {
        return spouse;
    }

    protected PropertyBuilder<Gender> getGender() {
        return gender;
    }

    @Override
    protected void assign(T instance) {
        instance.setSpouse(spouse.getOrDefault(null));
    }

    public static final class PersonBuilderType extends AbstractPersonBuilder<PersonBuilderType, Person> {
        public PersonBuilderType() {
        }

        public PersonBuilderType(Person template) {
            super(template);
        }

        public static PersonBuilderType aPerson() {
            return new PersonBuilderType();
        }

        public static PersonBuilderType aPersonFrom(Person template) {
            return new PersonBuilderType(template);
        }

        protected Person construct() {
            return new Person(getName().getOrDefault("Bob"),
                    getGender().getOrDefault(Gender.Male));
        }
    }
}