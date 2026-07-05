package ru.otus.spring.integration.hw.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component(value = "preschool")
public class PreSchool extends School {

    @Override
    public Collection<Child> teach(Collection<Child> children) {
        children.stream().filter(child -> child.getEducation().equals(EducationType.NONE))
                .forEach(child -> child.setEducation(EducationType.EARLY));
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            log.error("Study interrupted");
        }
        log.info("Completed study, graduate class: {}", children);
        return children;
    }

}
