package ru.baranova.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.repository.FileRepo;


@Controller
@RequestMapping("/files/view")
public class HtmlController {

    @Autowired
    private FileRepo fileRepo;

    // чтобы таблица не была громоздкой в объектах папка и метадата отображены только некоторые поля
    // для объекта папка отображено только имя, 
    // для метадаты только размер

    @GetMapping("/list")
    public String fileListView(Model model) {
        {
            Iterable<File> files = fileRepo.findAll();
            model.addAttribute("files", files);
            return "fileList";
        }
    }
    
}
