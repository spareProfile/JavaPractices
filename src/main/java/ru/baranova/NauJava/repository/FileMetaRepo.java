package ru.baranova.NauJava.repository;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.FileMeta;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="fileMeta")
public interface FileMetaRepo extends CrudRepository<FileMeta, Long> {
    
    
}
