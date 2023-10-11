package com.spring.paint;

import com.spring.paint.config.PaintFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
        Application.launch(PaintFxApplication.class, args);
    }
}

