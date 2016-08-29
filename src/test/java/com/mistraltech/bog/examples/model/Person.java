package com.mistraltech.bog.examples.model;

import java.beans.ConstructorProperties;

public class Person {
    private String name;
    private Person spouse;
    private Gender gender;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                // Just display name of spouse to avoid infinite recursion...
                ", spouse=" + (spouse == null ? null : spouse.getName()) +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}

