package ru.baranova.NauJava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import jakarta.persistence.CascadeType;

@Entity
@Table(name = "Files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String link;

    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
   // private User uploadedBy;


    @Column
    private Long uploadedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_meta_id", nullable = false)
    private FileMeta file_meta;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

    public File() {}

    public File(String name, Folder folder) {
        this.id = System.currentTimeMillis() + 123;
        this.name = name;
        this.link = "https://naujava.ru/" + name;
        this.uploadedAt = System.currentTimeMillis();
        this.folder = folder;
        this.file_meta = null;

    }

    public File(Long id, String name, FileMeta fileMeta, Folder folder) {
        this.id = id;
        this.name = name;
        this.link = "https://naujava.ru/" + name;
        this.uploadedAt = System.currentTimeMillis();
        this.file_meta = fileMeta;
        this.folder = folder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public Long getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Long uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public FileMeta getFileMeta() {
        return file_meta;
    }

    public void setFileMeta(FileMeta fileMeta) {
        this.file_meta = fileMeta;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
    

}