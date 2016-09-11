package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class SayHiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SayHiApplication.class, args);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, String> respondWithUUID() {
        Map response = new HashMap<String, String>();
        response.put("id", UUID.randomUUID().toString());
        return response;
    }
}
