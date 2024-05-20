package ldubgd.coursework.TestFTE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestFteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestFteApplication.class, args);
	}

}
