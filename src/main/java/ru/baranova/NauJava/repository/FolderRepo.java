package ru.baranova.NauJava.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.Folder;


public interface FolderRepo extends CrudRepository<Folder, Long> {
    List<Folder> findByfolderName(String folderName);
    
}
