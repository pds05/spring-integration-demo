package ru.otus.spring.integration.hw.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.hw.models.Child;

import java.util.Collection;

@Slf4j
@Service(value = "highSchool")
public class HighSchool extends School {

    @Override
    public Collection<Child> teach(Collection<Child> children) {
        children.stream().filter(child -> child.getEducation().equals(EducationType.SECONDARY))
                .forEach(child -> child.setEducation(EducationType.HIGH));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Study interrupted");
        }
        log.info("Completed study, graduate class: {}", children);
        return children;
    }
}
