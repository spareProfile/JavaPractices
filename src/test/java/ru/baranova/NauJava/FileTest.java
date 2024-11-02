package ru.baranova.NauJava;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.entity.User;
import ru.baranova.NauJava.entity.Role;
import ru.baranova.NauJava.entity.Folder;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.repository.UserRepo;
import ru.baranova.NauJava.repository.RoleRepo;
import ru.baranova.NauJava.repository.FolderRepo;
import ru.baranova.NauJava.repository.FileMetaRepo;
import ru.baranova.NauJava.entity.FileMeta;

import ru.baranova.NauJava.dao.FileCustomRepoImpl;
import ru.baranova.NauJava.Service.FileService;

@SpringBootTest
public class FileTest {
    private final FileRepo fileRepo;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final FolderRepo folderRepo;
    private final FileMetaRepo fileMetaRepo;
    private final FileCustomRepoImpl fileCustomRepoImpl;

    private final FileService fileService;

    @Autowired
    FileTest(FileRepo fileRepo, RoleRepo roleRepo, UserRepo userRepo, FolderRepo folderRepo, FileMetaRepo fileMetaRepo, 
            FileCustomRepoImpl fileCustomRepoImpl, FileService fileService) {
        this.fileRepo = fileRepo;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.folderRepo = folderRepo;
        this.fileMetaRepo = fileMetaRepo;
        this.fileCustomRepoImpl = fileCustomRepoImpl;

        this.fileService = fileService;
    }

    Role getDefaultRole() {
        List<Role> roles = roleRepo.findByroleName("default");
        Role role;
        if (roles.isEmpty()) { // create default role
            role = new Role("default");
            roleRepo.save(role);
        }
        else {
            role = roles.get(0);
        }
        return role;
    }

    // repository test
    @Test
    void testRepositories_5section() {
        Role role = getDefaultRole();
        String name = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString() + "@gmail.ru";
        User user = new User(name, email, role); // create User Username, password, role
        userRepo.save(user);

        // create new folder
        String folderName = UUID.randomUUID().toString();
        Folder folder = new Folder(user, folderName);
        folderRepo.save(folder);


        String fileName = UUID.randomUUID().toString();
        File file = new File(fileName, folder); // create File 

        FileMeta fileMeta = new FileMeta(1000, "text", "Ru", file);
        file.setFileMeta(fileMeta);
        fileMetaRepo.save(fileMeta);

        fileRepo.save(file);
        // find file by filename 
        File foundFile = fileRepo.findByname(fileName).get(0);
        Assertions.assertEquals(fileName, foundFile.getName());

        // find file by filename and uploadedAt
        File fileFondByNameAndUploadedAt = fileRepo.findByNameAndUploadedAt(fileName, file.getUploadedAt()).get(0);
        Assertions.assertEquals(fileName, fileFondByNameAndUploadedAt.getName()); 

        // find File by Folder.foldername
        File fileFounDByFolder = fileRepo.findByFolderName(folderName).get(0);
        Assertions.assertEquals(fileName, fileFounDByFolder.getName());
       
        
        
    }
    // custom repository test
    @Test
    void testRepositories_6section() {
        Role role = getDefaultRole();
        String name = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString() + "@gmail.ru";
        User user = new User(name, email, role); // create User Username, password, role
        userRepo.save(user);

        // create new folder
        String folderName = UUID.randomUUID().toString();
        Folder folder = new Folder(user, folderName);
        folderRepo.save(folder);


        String fileName = UUID.randomUUID().toString();
        File file = new File(fileName, folder); // create File 

        int size = new Random().nextInt(1000);
        FileMeta fileMeta = new FileMeta(size, "text", "Ru", file);
        file.setFileMeta(fileMeta);
        fileMetaRepo.save(fileMeta);

        fileRepo.save(file);
        // custom Repository test
        // find file by filename 
        File foundFile = fileCustomRepoImpl.findByFileMetaSizeAndUploadedAt(size, file.getUploadedAt()).get(0);
        Assertions.assertEquals(fileName, foundFile.getName());

        //find file by filename and uploadedAt
        File foundByFolderName = fileCustomRepoImpl.findByFolderName(folderName).get(0);
        Assertions.assertEquals(fileName, foundByFolderName.getName()); 
    }
    @Test
    void testTransactional_7section() {
        Role role = getDefaultRole();
        String name = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString() + "@gmail.ru";
        User user = new User(name, email, role); // create User Username, password, role
        userRepo.save(user);

        // create new folder
        String folderName = UUID.randomUUID().toString();
        Folder folder = new Folder(user, folderName);
        folderRepo.save(folder);


        String fileName = UUID.randomUUID().toString();
        
        // successful transaction 
        fileService.createFile(fileName, folderName);

        // assert that file is created
        File foundFile = fileRepo.findByname(fileName).get(0);
        Assertions.assertEquals(fileName, foundFile.getName());

        // failed transaction 
        String nonExsistingfolderName = UUID.randomUUID().toString();
        String fileNameFail = UUID.randomUUID().toString();

        fileService.createFile(fileNameFail, nonExsistingfolderName);

        List<File> notFount = fileRepo.findByname(fileNameFail);
        Assertions.assertTrue(notFount.isEmpty());





       
        
        
    }


    
}
