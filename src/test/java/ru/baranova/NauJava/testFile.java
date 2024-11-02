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
import ru.baranova.NauJava.entity.Report;
import ru.baranova.NauJava.entity.Folder;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.repository.UserRepo;
import ru.baranova.NauJava.repository.RoleRepo;
import ru.baranova.NauJava.repository.FolderRepo;
import ru.baranova.NauJava.repository.ReportRepo;
import ru.baranova.NauJava.repository.FileMetaRepo;
import ru.baranova.NauJava.entity.FileMeta;

import ru.baranova.NauJava.dao.FileCustomRepoImpl;
import ru.baranova.NauJava.Service.FileService;
import ru.baranova.NauJava.Service.ReportService;

public class testFile {
    private final FileRepo fileRepo;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final FolderRepo folderRepo;
    private final FileMetaRepo fileMetaRepo;
    private final ReportRepo reportRepo;    

    private final ReportService reportService;
    private final FileCustomRepoImpl fileCustomRepoImpl;

    private final FileService fileService;

    testFile(FileRepo fileRepo, RoleRepo roleRepo, UserRepo userRepo, FolderRepo folderRepo, FileMetaRepo fileMetaRepo, 
            FileCustomRepoImpl fileCustomRepoImpl, FileService fileService, ReportRepo reportRepo, ReportService reportService) {
        this.fileRepo = fileRepo;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.folderRepo = folderRepo;
        this.fileMetaRepo = fileMetaRepo;
        this.fileCustomRepoImpl = fileCustomRepoImpl;

        this.fileService = fileService;
        this.reportRepo = reportRepo;
        this.reportService = reportService;

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
    public void main() {
        Long ids = reportService.createReport();
        System.out.println("Report created with id: " + ids);
        
        
    }


    
}
