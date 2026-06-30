package ru.otus.spring.integration.hw.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.spring.integration.hw.models.Child;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class CommunitySimulator implements Community {

    private Family family;

    private SchoolGateway schoolGateway;

    public static final int GROUPS_COUNT = 3;
    public static final int GROUP_SIZE = 15;

    @Override
    public void progress() {
        for (int i = 0; i < GROUPS_COUNT; i++) {
            List<Child> children = collectGroup();
            log.info("Sending to study: {}", children);
            schoolGateway.sendStudy(children);
        }
    }

    private List<Child> collectGroup() {
        List<Child> group = new ArrayList<>();
        for (int i = 0; i < GROUP_SIZE; i++) {
            group.add(family.getChild());
        }
        return group;
    }
}
