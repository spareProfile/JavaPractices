package ru.baranova.NauJava.entity;
import jakarta.persistence.Entity;

import java.util.List;

import javax.persistence.GenerationType;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String roleName;
    
    @Column
    private boolean owner;
    @Column
    private boolean edit;
    @Column
    private boolean read;
    @Column
    private boolean list;

    @OneToMany(mappedBy = "user_role")
    private List<User> users;

    public Role() {
    }

    public Role(String roleName) {
        //this.id = System.currentTimeMillis();
        this.roleName = roleName;
        this.owner = false;
        this.edit = false;
        this.read = true;
        this.list = true;
    }


    public Role(Long id, String roleName, boolean owner, boolean edit, boolean read, boolean list) {
        this.id = id;
        this.roleName = roleName;
        this.owner = owner;
        this.edit = edit;
        this.read = read;
        this.list = list;
    }

    public String toString() {
        return "Role(" +
                "id=" + id +
                ", name='" + roleName + '\'' +
                ')';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return roleName;
    }

    public void setName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
