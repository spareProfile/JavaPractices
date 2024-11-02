package ru.baranova.NauJava.Service;
import ru.baranova.NauJava.entity.ReportStatus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import ru.baranova.NauJava.entity.Report;
import org.springframework.scheduling.annotation.Async;


public interface ReportService {
    
    // create record in databse and return its id 
    Long createReport();

    ReportStatus getReportStatus(Long id);
    
    String getReportContent(Long id);

    @Async
    CompletableFuture<Void> formReport(Long id) throws InterruptedException, ExecutionException;
    
}