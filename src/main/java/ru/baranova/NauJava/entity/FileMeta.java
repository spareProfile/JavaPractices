package ru.baranova.NauJava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.persistence.Column;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FileMeta")
public class FileMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int size;

    @Column
    private String extension;

    @Column
    private String region;

    @Column
    private List<String> tags; // todo add tags to database

    @JsonIgnore
    @XmlTransient
    @OneToOne(mappedBy = "file_meta")
    private File file;

    public FileMeta() {
    }

    public FileMeta(File file) {
        this.id = System.currentTimeMillis();
        this.size = 0;
        this.extension = null;
        this.region = null;
        this.tags = null;
        this.file = file;

    }

    public FileMeta(int size, String extension, String region, File file) {
        this.id = System.currentTimeMillis();
        this.size = size;
        this.extension = extension;
        this.region = region;
        this.tags = null;
        this.file = file;

    }

    public FileMeta(Long id, int size, String extension, String region, List<String> tags, File file) {
        this.id = id;
        this.size = size;
        this.extension = extension;
        this.region = region;
        this.tags = tags;
        this.file = file;
    }

    // Constructors, getters, and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}