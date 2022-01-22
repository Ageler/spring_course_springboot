package com.andrewborchenko.spring.springboot.spring_course_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// В данную аннотацию входит @Configuration + @EnableAutoConfiguration + @ComponentScan
// если необходимо отсканировать доп пакеты прописываем @SpringBootApplication(scanBasePackages = "названия пакетов")
@SpringBootApplication
public class SpringCourseSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCourseSpringbootApplication.class, args);
    }

}
