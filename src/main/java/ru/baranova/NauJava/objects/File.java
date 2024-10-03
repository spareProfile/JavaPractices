package ru.baranova.NauJava.objects;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class File {
    private Long id;
    private String name;
    private String link;
    private Map <String, List<Long>> access;

    public File(Long id, String name, String link, Long ownerId) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.access = new HashMap<>();

        this.access.put("owner", new ArrayList<>());
        this.access.put("editor", new ArrayList<>());
        this.access.put("reader", new ArrayList<>());

        this.access.get("owner").add(ownerId);
        this.access.get("editor").add(ownerId);
        this.access.get("reader").add(ownerId);

    }

    public String toString() {
        return "File(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", access=(Owner=" + access.get("owner") + 
                        ", Editor=" + access.get("editor") + 
                        ", Reader=" + access.get("reader") + ')' +
                ')';
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

    public Map<String, List<Long>> getAccess() {
        return access;
    }

    public void setAccess(Map<String, List<Long>> access) { // add set access for the new user to the file
        this.access = access;
    }
}


