package ru.baranova.NauJava.dao;
import java.util.List;
import ru.baranova.NauJava.entity.File;

public interface FileCustomRepo {
    List<File> findByFolderName(String folderName);

    List<File> findByFileMetaSizeAndUploadedAt(int size, Long uploadedAt);

}
