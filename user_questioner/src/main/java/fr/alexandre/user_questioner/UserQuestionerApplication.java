package fr.alexandre.user_questioner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class UserQuestionerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserQuestionerApplication.class, args);
	}

}
