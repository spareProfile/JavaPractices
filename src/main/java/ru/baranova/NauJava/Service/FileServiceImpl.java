package ru.baranova.NauJava.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.entity.FileMeta;
import ru.baranova.NauJava.entity.Folder;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.repository.FolderRepo;
import ru.baranova.NauJava.repository.FileMetaRepo;

@Service
public class FileServiceImpl implements FileService{
    private final FileRepo fileRepo;
    private final FolderRepo folderRepo;
    private final FileMetaRepo fileMetaRepo;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public FileServiceImpl(FileRepo fileRepo, FolderRepo folderRepo, FileMetaRepo fileMetaRepo, PlatformTransactionManager transactionManager) {
        this.fileRepo = fileRepo;
        this.folderRepo = folderRepo;
        this.fileMetaRepo = fileMetaRepo;
        this.transactionManager = transactionManager;
    }

    public void createFile(String name, String folderName) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<Folder> folders = folderRepo.findByfolderName(folderName);
            File file = new File(name, folders.get(0));
            FileMeta fileMeta = new FileMeta(file);
            file.setFileMeta(fileMeta);
            fileMetaRepo.save(fileMeta);
            fileRepo.save(file);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }
}


}