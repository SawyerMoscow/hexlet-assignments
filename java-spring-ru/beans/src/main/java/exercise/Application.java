package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    public Daytime getFaker() { // Имя метода не важно
        int xyi = LocalDateTime.now().getHour();
        if (xyi >= 0 && xyi <= 6) {
            return new Night();
        }
        else {
            return new Day();
        }
    }
    // END
}
