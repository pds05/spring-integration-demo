package ru.otus.spring.integration.hw.models;

import java.util.Collection;

public interface SchoolLearning {

    Collection<Child> teach(Collection<Child> children);

}
