package by.anpoliakov.petshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*  Использование этой аннотации эквивалентно:

*   @Configuration - позволяет регистрировать дополнительные компоненты (beans) в контексте или импортировать дополнительные классы конфигурации.
*   @EnableAutoConfiguration - включить механизм автоконфигурации Spring Boot.
*   @ComponentScan - включить сканирование @Component для пакета, в котором находится приложение.
*/

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
