package ru.baranova.NauJava.entity;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDateTime;


@Entity
@Table(name = "Reports")
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private ReportStatus status;

    @Column
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private Integer UserCount;

    @ManyToMany
    private List<File> files;

    @Column
    private Long UserSearchTime;

    @Column
    private Long FileSearchTime;

    @Column
    private Long FullTime;

    public Report(ReportStatus status, String content, Long UserSearchTime, Long FileSearchTime) {
        this.status = status;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.UserSearchTime = UserSearchTime;
        this.FileSearchTime = FileSearchTime;
        this.UserCount = null;
        this.files = null;
        this.FullTime = null;
    }

    public Report() {
        this.status = ReportStatus.CREATED;
        this.content = null;
        this.createdAt = LocalDateTime.now();
        this.UserSearchTime = null;
        this.FileSearchTime = null;
        this.UserCount = null;
        this.files = null;
        this.FullTime = null;
    }

    public String formContent() {
        if (content == null) {
            this.content = "Users: " + UserCount + " Files: " + files + " Search user time: " + UserSearchTime + " Search file time: " + FileSearchTime;
        }
        return content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserSearchTime() {
        return UserSearchTime;
    }

    public void setUserSearchTime(Long UserSearchTime) {
        this.UserSearchTime = UserSearchTime;
    }

    public Long getFileSearchTime() {
        return FileSearchTime;
    }

    public void setFileSearchTime(Long FileSearchTime) {
        this.FileSearchTime = FileSearchTime;
    }

    public Integer getUserCount() {
        return UserCount;
    }

    public void setUserCount(Integer UserCount) {
        this.UserCount = UserCount;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Long getFullTime() {
        return this.FullTime;
    }

    public void setFullTime(Long FullTime) {
        this.FullTime = FullTime;
    }

    public String getFilesStr() {
        return this.files.stream().map(File::getName).collect(Collectors.toList()).toString();
    }
    
}
