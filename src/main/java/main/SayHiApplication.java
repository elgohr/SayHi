package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SayHiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SayHiApplication.class, args);
    }

    @RequestMapping(path = "/")
    public String sayHi() {
        return "hi";
    }
}
