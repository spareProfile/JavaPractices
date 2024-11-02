package ru.baranova.NauJava.Service;

import ru.baranova.NauJava.entity.ReportStatus;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ru.baranova.NauJava.entity.Report;
import ru.baranova.NauJava.entity.User;
import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.repository.ReportRepo;
import ru.baranova.NauJava.repository.RoleRepo;
import ru.baranova.NauJava.repository.UserRepo;
import java.util.concurrent.CompletableFuture;


@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepo reportRepo;
    private UserRepo userRepo;
    private FileRepo fileRepo;

    @Autowired
    public ReportServiceImpl(ReportRepo reportRepo, UserRepo userRepo, FileRepo fileRepo) {
        this.reportRepo = reportRepo;    
        this.userRepo = userRepo;
        this.fileRepo = fileRepo;    
    }
    @Override
    public Long createReport() {
        Report report = new Report();
        reportRepo.save(report);
        return report.getId();
    }
    @Async
    @Override
    public CompletableFuture<Void> formReport(Long id) throws InterruptedException, ExecutionException {
        Report report = reportRepo.findById(id).get();  
        Thread thr1 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            List <User> users = StreamSupport.stream(userRepo.findAll().spliterator(), false).collect(Collectors.toList());
            long endTime = System.currentTimeMillis();
            report.setUserSearchTime(endTime - startTime);
            report.setUserCount(users.size());
        });
        Thread thr2 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            List <File> files = StreamSupport.stream(fileRepo.findAll().spliterator(), false).collect(Collectors.toList());
            long endTime = System.currentTimeMillis();
            report.setFileSearchTime(endTime - startTime);
            report.setFiles(files);

        });
        thr1.start();
        thr2.start();

        CompletableFuture<Void> awaitable = CompletableFuture.runAsync(() -> {
            try{
                long start = System.currentTimeMillis();
                thr1.join();
                thr2.join();
                String fileNames = report.getFiles().stream().map(File::getName).collect(Collectors.toList()).toString();
                long end = System.currentTimeMillis();
                report.setFullTime(end - start);
                String content = "Users: " + report.getUserCount() + " Files: [" + fileNames + "] Search user time: " + report.getUserSearchTime() + "ms. Search file time: " + report.getFileSearchTime() + "ms" + "Search full time: " + report.getFullTime() + "ms" ;
                if (content.length() > 200) { content = content.substring(0, 200); }
                report.setContent(content);
                report.setStatus(ReportStatus.DONE);
                reportRepo.save(report);
            }
            catch (InterruptedException e) {
                report.setStatus(ReportStatus.ERROR); // set error status
                reportRepo.save(report);
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        });

    return CompletableFuture.allOf(awaitable).completedFuture(null);
    }

    @Override
    public ReportStatus getReportStatus(Long id){
        Report report = reportRepo.findById(id).get();
        System.out.println("Report status is " + report.getStatus());
        return report.getStatus();
    }

    @Override
    public String getReportContent(Long id){
        Report report = reportRepo.findById(id).get();
        System.out.println("Report status is " + report.getContent());
        return report.getContent();
    }


}
