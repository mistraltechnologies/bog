package com.mistraltech.bog.examples.model;

import java.beans.ConstructorProperties;

public class Person {
    private String name;
    private Person spouse;
    private Gender gender;

    @ConstructorProperties({"name", "gender"})
    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", spouse=" + spouse +
                ", gender=" + gender +
                '}';
    }
}

