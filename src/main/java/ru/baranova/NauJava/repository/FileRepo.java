package ru.baranova.NauJava.repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.baranova.NauJava.entity.File;

public interface FileRepo extends CrudRepository<File, Long> {
        
        @Query("SELECT f FROM File f WHERE f.name = :name")
        List<File> findByname(@Param("name") String name);

        // find files by folder name
        @Query("SELECT f FROM File f WHERE f.folder.folderName = :folderName")
        List<File> findByFolderName(@Param("folderName") String folderName);

        // find by username of user that upload this file and date of upload (timestamp)
        List<File> findByNameAndUploadedAt(String name, Long uploadedAt);
    
}
