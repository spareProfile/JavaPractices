package ru.baranova.NauJava.repository;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.FileMeta;

public interface FileMetaRepo extends CrudRepository<FileMeta, Long> {
    
    
}
