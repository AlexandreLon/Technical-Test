package fr.alexandre.user_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.Clock;

@EnableWebMvc
@SpringBootApplication
public class UserRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistryApplication.class, args);
	}

}
