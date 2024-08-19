package myenglish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class MyEnglishServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEnglishServerApplication.class, args);
	}
	
}
