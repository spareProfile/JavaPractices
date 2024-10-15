package ru.baranova.NauJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.dao.FileCustomRepo;


@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileCustomRepo fileCustomRepo;

    @Autowired
    private FileRepo fileRepo;

    @GetMapping("/findBy/name")
    public List<File> findByName(@RequestParam String name) {
        return fileRepo.findByname(name);
    }

    @GetMapping("/findBy/folder")
    public List<File> findByFolderName(@RequestParam String folder) {
        return fileRepo.findByFolderName(folder);
    }

    @GetMapping("/findBy/name/uploadedAt")
    public List<File> findByNameAndUploadedAt(@RequestParam String name , @RequestParam Long uploadedAt) {
        return fileRepo.findByNameAndUploadedAt(name, uploadedAt);
    }

    // custom repository 
    @GetMapping("/findBy/metaSize/uploadedAt") 
    public List<File> findByFileMetaSizeAndUploadedAt(@RequestParam int size , @RequestParam Long uploadedAt) {
        return fileCustomRepo.findByFileMetaSizeAndUploadedAt(size, uploadedAt);
    }

    // custom repository 
    @GetMapping("/findBy/custom/folder") 
    public List<File> findByCustomFolderName(@RequestParam String name) {
        return fileCustomRepo.findByFolderName(name);
    }


    @GetMapping("/all")
    public Iterable<File> fileListView() {
        {
            return fileRepo.findAll();
        }
    }
    
}
