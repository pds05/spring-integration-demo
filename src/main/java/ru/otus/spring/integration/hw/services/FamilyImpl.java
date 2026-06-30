package ru.otus.spring.integration.hw.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.hw.models.Child;

import java.util.Random;

@AllArgsConstructor
@Service
public class FamilyImpl implements Family {

    private static final String[] FIRST_NAME = {"Ваня", "Маша", "Коля", "Даша", "Вася", "Рита"};

    private static final String[] LAST_NAME = {"Иванов[а]", "Петров[а]", "Сидоров[а]", "Федоров[а]", "Александров[а]", "Данилов[а]"};

    @Override
    public Child getChild() {
        return new Child(FIRST_NAME[new Random().nextInt(FIRST_NAME.length)]
                .concat(" ")
                .concat(LAST_NAME[new Random().nextInt(LAST_NAME.length)]),
                School.EducationType.values()[new Random().nextInt(School.EducationType.values().length - 1)]
        );
    }
}
