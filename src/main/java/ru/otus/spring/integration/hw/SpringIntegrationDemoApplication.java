package ru.otus.spring.integration.hw;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.integration.hw.models.Community;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringIntegrationDemoApplication implements CommandLineRunner {

	private final Community community;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		community.progress();
	}
}
