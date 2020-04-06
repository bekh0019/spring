package com.epam;

import com.epam.converter.PdfMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public HttpMessageConverters additionalConverter(){
        return new HttpMessageConverters(new PdfMessageConverter());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}