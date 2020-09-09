package io.kwangsik.loginrestapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LoginRestApiApplication {
    // TODO 삭제요망
    @Value("${spring.thymeleaf.cache}")
    private static boolean thymeleafCache;

    public static void main(String[] args) {
        SpringApplication.run(LoginRestApiApplication.class, args);

        // TODO 삭제요망
        log.info("$$ thymeleafCache = " + thymeleafCache);
    }
}
