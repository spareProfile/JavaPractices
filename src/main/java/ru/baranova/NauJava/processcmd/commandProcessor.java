package ru.baranova.NauJava.processcmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.baranova.NauJava.Service.FileService;

@Component
public class commandProcessor {
    private final FileService fileService;

    @Autowired
    public commandProcessor(FileService fileService) {
        this.fileService = fileService;
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");
        switch (commandParts[0]) {
            case "help":
                System.out.println("Доступные команды: \n" +
                        "create fileID fileName fileLink OwnerID - создать файл\n" +
                        "delete fileID - удалить файл\n" +
                        "read fileID - прочитать файл\n" +
                        "updateLink fileID newLink - обновить ссылку на файл\n" +
                        "getAll - получить все файлы\n" +
                        "exit - выход");
                break;
            case "create":
                try{ fileService.createFile(Long.parseLong(commandParts[1]), commandParts[2], commandParts[3], Long.parseLong(commandParts[4]));}
                catch (ArrayIndexOutOfBoundsException e) { System.out.println("Чтобы корректно создать файл передайте команду в слудующем виде:'create fileID fileName fileLink OwnerID' ");}
                catch (NumberFormatException e) { System.out.println("Не верный тип параметров fileID, OwnerID должен быть типа Long. FileName, fileLink - типа String" );}
                break;
            case "delete":
                try{ fileService.deleteFileById(Long.parseLong(commandParts[1]));}
                catch (ArrayIndexOutOfBoundsException e) { System.out.println("Чтобы удалить файл передайте команду в слудующем виде:'delete fileID' ");}
                catch (NumberFormatException e) { System.out.println("Не верный тип параметров fileID должен быть типа Long." );}
                break;
            case "read":
                try{var file = fileService.getFileById(Long.parseLong(commandParts[1]));
                    System.out.println((Optional.ofNullable(file)).map(Object::toString).orElse(""));
                    }
                catch (ArrayIndexOutOfBoundsException e) { System.out.println("Чтобы корректно прочитать файл передайте команду в следуюзем виде:'read fileID' ");}
                catch (NumberFormatException e) { System.out.println("Не верный тип параметров fileID должен быть типа Long." );}
                break;
            case "updateLink":
                try{ fileService.updateLinkById(Long.parseLong(commandParts[1]), commandParts[2]);}
                catch (ArrayIndexOutOfBoundsException e) { System.out.println("Чтобы корректно обновить ссылку на файл передайте команды в следуюзем виде:'updateLink fileID newLink' " );}
                catch (NumberFormatException e) { System.out.println("Не верный тип параметров fileID должен быть типа Long и newLink должен быть типа String." );}
                break;
            case "getAll":
                for ( var fileMap : fileService.getAllFiles().entrySet()) {
                    var file = fileMap.getValue();
                    System.out.println(file.toString());
                }
                break;
            default:
                System.out.println("Неизвестная команда");
        }
    }
    
}
