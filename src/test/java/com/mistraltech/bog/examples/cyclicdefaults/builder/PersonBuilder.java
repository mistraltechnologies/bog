package com.mistraltech.bog.examples.cyclicdefaults.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.propertybuilder.PropertyBuilder;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

import static com.mistraltech.bog.core.propertybuilder.PropertyBuilder.propertyBuilder;
import static com.mistraltech.bog.core.picker.IntegerValuePicker.integerValuePicker;
import static com.mistraltech.bog.core.picker.RegexStringValuePicker.regexStringValuePicker;

public final class PersonBuilder extends AbstractBuilder<Person> {
    private PropertyBuilder<Person> spouse = propertyBuilder();
    private PropertyBuilder<Integer> age = propertyBuilder(integerValuePicker(18, 40));
    private PropertyBuilder<String> name = propertyBuilder(() -> age.get() > 30 ? "Bill" : "Bob");
    private PropertyBuilder<Gender> gender = propertyBuilder(() -> name.get().equals("Bill") ? Gender.Male : Gender.Female);

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
        return new Person(name.get(), gender.get());
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.get());
    }
}