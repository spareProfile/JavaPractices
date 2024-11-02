package ru.baranova.NauJava.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ru.baranova.NauJava.entity.Report;
import ru.baranova.NauJava.repository.ReportRepo;

@Controller
@RequestMapping("/report/view")
public class HtmlReportController {
    
    @Autowired
    private ReportRepo reportRepo;

    @GetMapping("/list") // to save space listed only filenames for the file objects but fileobject can be fully listed in the html file or any of its fileds can be listed
    public String reportListView(Model model) {
        Iterable<Report> reports = reportRepo.findAll();
        List<Report> reportList = StreamSupport.stream(reports.spliterator(), false)
                        .collect(Collectors.toList());
        model.addAttribute("reports", reportList);
        return "reportList";
        }

    @GetMapping("/find")
    public String userFriendlyFindByID(@RequestParam Long id, Model model) {
        Optional<Report> report = reportRepo.findById(id);
        List<Report> reportList = report.isPresent() ? Collections.singletonList(report.get()) : Collections.emptyList();
        model.addAttribute("reports", reportList);
        return "reportList";
        }
}
