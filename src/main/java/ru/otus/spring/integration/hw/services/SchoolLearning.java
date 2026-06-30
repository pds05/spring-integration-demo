package ru.otus.spring.integration.hw.services;

import ru.otus.spring.integration.hw.models.Child;

import java.util.Collection;

public interface SchoolLearning {

    Collection<Child> teach(Collection<Child> children);

}
