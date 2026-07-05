package ru.otus.spring.integration.hw.models;

public abstract class School implements SchoolLearning {

    public enum EducationType {
        NONE, EARLY, BASIC, SECONDARY, HIGH
    }
}
