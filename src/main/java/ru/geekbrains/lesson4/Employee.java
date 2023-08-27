package ru.geekbrains.lesson4;

public class Employee {

    private String name;

    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
