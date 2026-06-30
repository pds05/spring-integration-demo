package ru.otus.spring.integration.hw.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.hw.models.Child;

import java.util.Collection;

@Slf4j
@Service(value = "basicSchool")
public class BasicSchool extends School {

    @Override
    public Collection<Child> teach(Collection<Child> children) {
        for (Child child : children) {
            switch (child.getEducation()) {
                case NONE:
                case EARLY:
                    child.setEducation(EducationType.BASIC);
                    break;
            }
        }
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            log.error("Study interrupted");
        }
        log.info("Completed study, graduate class: {}", children);
        return children;
    }
}
