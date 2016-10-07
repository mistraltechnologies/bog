package com.mistraltech.bog.examples.defaultpicker.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.picker.IntegerRandomValuePicker;
import com.mistraltech.bog.examples.model.Gender;
import com.mistraltech.bog.examples.model.Person;

import static com.mistraltech.bog.core.picker.EnumRandomValuePicker.enumRandomValuePicker;
import static com.mistraltech.bog.core.picker.RegexStringRandomValuePicker.regexStringRandomValuePicker;
import static com.mistraltech.bog.core.ValueContainer.valueContainer;

public final class PersonBuilder extends AbstractBuilder<Person> {
    private ValueContainer<String> name = ValueContainer.valueContainer(regexStringRandomValuePicker("Bob|Bill"));

    private ValueContainer<Person> spouse = valueContainer();

    private ValueContainer<Gender> gender = ValueContainer.valueContainer(enumRandomValuePicker(Gender.class));

    private ValueContainer<Integer> age = ValueContainer.valueContainer(this::getDefaultAge);

    private IntegerRandomValuePicker ageDefaultPicker = IntegerRandomValuePicker.integerRandomValuePicker(1, 100);

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

    protected Integer getDefaultAge() {
        return ageDefaultPicker.get();
    }

    @Override
    protected Person construct() {
        return new Person(
                name.get(),
                gender.get());
    }

    @Override
    protected void assign(Person instance) {
        instance.setSpouse(spouse.get());
        instance.setAge(age.get());
    }
}