package ru.otus.spring.integration.hw.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.otus.spring.integration.hw.services.School;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Child {

    private String name;

    private School.EducationType education;

}
