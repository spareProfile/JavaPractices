package ru.baranova.NauJava.repository;
import ru.baranova.NauJava.objects.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileRepository implements CrudRepository<File, Long> {
    private final Map<Long, File> fileContainer;

    @Autowired
    public FileRepository(Map<Long, File> fileContainer) {
        this.fileContainer = fileContainer;
    }

    @Override
    public void create(File fileObj) {
        if (fileContainer.containsKey(fileObj.getId())){
            System.out.println("Файл с таким ID уже существует");
        }
        else {
            fileContainer.put(fileObj.getId(), fileObj);
            System.out.println("Файл c ID " + fileObj.getId().toString() + " успешно добавлен");
        }
    }

    @Override
    public File read(Long id) {
        if (fileContainer.containsKey(id)){
            return fileContainer.get(id);
        }
        else {
            System.out.println("Файла с таким ID не существует");
            return null;
        }
    }

    @Override
    public void update(File fileObj) {
        Long id = fileObj.getId();
        if (fileContainer.containsKey(id)){
            fileContainer.put(id, fileObj);
            System.out.println("Файл успешно обновлен");
        }
        else {
            System.out.println("Файла с таким ID не существует");
        }
    }

    @Override
    public void delete(Long id) {
        if (fileContainer.containsKey(id)){
            fileContainer.remove(id);
            System.out.println("Файл успешно удален");
        }
        else {
            System.out.println("Файла с таким ID не существует");
        }
    }

    public Map<Long, File> getAllFiles() {
        return fileContainer;
    }
    
}
