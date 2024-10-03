package ru.baranova.NauJava.Service;
import ru.baranova.NauJava.objects.File;
import ru.baranova.NauJava.repository.FileRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileService implements FileServiceInterface {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void createFile(Long fileID, String name, String link, Long ownerId) {
        File newFile = new File(fileID, name, link, ownerId);
        fileRepository.create(newFile);
    }

    @Override
    public void deleteFileById(Long fileID) {
        fileRepository.delete(fileID);
    }

    @Override
    public File getFileById(Long fileID) {
        return fileRepository.read(fileID);
    }

    @Override
    public void updateLinkById(Long fileID, String newLink) {
        File file = fileRepository.read(fileID);
        if (file == null) {return;}
        file.setLink(newLink);
        fileRepository.update(file);
    }

    @Override
    public Map<Long, File> getAllFiles() {
        return fileRepository.getAllFiles();
    }

    

    
}
