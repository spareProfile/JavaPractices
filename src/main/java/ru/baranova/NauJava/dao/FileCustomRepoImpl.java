package ru.baranova.NauJava.dao;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.entity.Folder;
import ru.baranova.NauJava.entity.FileMeta;

@Repository
public class FileCustomRepoImpl implements FileCustomRepo {

    private final EntityManager entityManager;

    @Autowired
    public FileCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<File> findByFolderName(String folderName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> cq = cb.createQuery(File.class);

        Root<File> fileRoot = cq.from(File.class);
        Join<File, Folder> folder = fileRoot.join("folder", JoinType.INNER);
        Predicate namePredicate = cb.equal(folder.get("folderName"), folderName);
        cq.select(fileRoot).where(namePredicate);
        return entityManager.createQuery(cq).getResultList();
        
        
    }

    @Override
    public List<File> findByFileMetaSizeAndUploadedAt(int size, Long uploadedAt) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> cq = cb.createQuery(File.class);

        Root<File> fileRoot = cq.from(File.class);
        Join<File, FileMeta> fileMeta = fileRoot.join("file_meta", JoinType.INNER);
        Predicate namePredicate = cb.equal(fileMeta.get("size"), size);
        Predicate uploadedAtPredicate = cb.equal(fileRoot.get("uploadedAt"), uploadedAt);

        cq.select(fileRoot).where(namePredicate, uploadedAtPredicate);
        return entityManager.createQuery(cq).getResultList();

        
    }

    
}
