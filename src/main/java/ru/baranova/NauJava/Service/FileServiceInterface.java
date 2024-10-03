package ru.baranova.NauJava.Service;
import ru.baranova.NauJava.objects.File;
import java.util.List;
import java.util.Map;

public interface FileServiceInterface {

    void createFile(Long fileID, String name, String link, Long ownerId);

    void deleteFileById(Long fileID);

    File getFileById(Long fileID);

    void updateLinkById(Long fileID, String newLink);

    Map<Long, File> getAllFiles();
    
}
