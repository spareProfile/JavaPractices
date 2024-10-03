package ru.baranova.NauJava.config;


import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import ru.baranova.NauJava.Service.FileService;
import ru.baranova.NauJava.processcmd.commandProcessor;

@Configuration
public class cmdConfig{

    @Autowired
    private commandProcessor commandProcessor;

    @Bean
    public CommandLineRunner commandScaner(FileService fileService) {
        return args -> 
        {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду 'exit' для выхода. 'help' - для получения списка команд");
                while (true) {
                    System.out.println("> ");
                    String input = scanner.nextLine();
                    if (input.equals("exit")) {
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };


        }
}