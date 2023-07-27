package edu.escuelaing.arsw;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SubastaExpress {
    public static void main(String[] args) {
       SpringApplication app = new SpringApplication(SubastaExpress.class);
       app.setDefaultProperties(Collections.singletonMap("server.port", getPort()));
       app.run(args);
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
