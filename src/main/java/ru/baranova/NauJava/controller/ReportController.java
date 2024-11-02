package ru.baranova.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.baranova.NauJava.Service.ReportService;
import ru.baranova.NauJava.entity.Report;
import ru.baranova.NauJava.entity.ReportStatus;
import ru.baranova.NauJava.repository.ReportRepo;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {
    // html view in html report controller
    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepo reportRepo;

    @GetMapping("/find")
    public Report findByID(@RequestParam Long id) {
        Optional<Report> report = reportRepo.findById(id);
        return report.orElse(null);
    }

    @GetMapping("/all")
    public Iterable<Report> fileListView() {
            return reportRepo.findAll();
        }

    @GetMapping("/create")
    public Long create() {
        Long id = reportService.createReport();
        try{
            reportService.formReport(id);
        }
        catch (Exception e) {
            Report report = reportRepo.findById(id).get(); 
            report.setStatus(ReportStatus.ERROR); // set error status
            reportRepo.save(report);
            e.printStackTrace();}
        return id;
        }
    
}
