package ru.baranova.NauJava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ru.baranova.NauJava.utils.PasswordGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String userName;

    @Column(nullable = false)
    private String password;

    //@OneToMany(mappedBy = "uploadedBy")
    //private List<File> uploadedFiles;

    @ManyToOne
    @JoinColumn(name = "user_role", nullable = true)
    private Role user_role;

    @OneToMany(mappedBy = "user")
    private List<Folder> folders;

    public User() {}

    public User(String userName, String email) {
        this.userName = userName;
        this.password = PasswordGenerator.generateRandomPassword(23);
        this.email = email;
        this.user_role = null; // default role
    }

    public User(String userName, String email, Role user_role) {
        this.userName = userName;
        this.password = PasswordGenerator.generateRandomPassword(23);
        this.email = email;
        this.user_role = user_role; // default role
    }

    public User(Long id, String userName, String password, String email, Role user_role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.user_role = user_role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoles() {
        return user_role;
    }

    public void setRoles(Role user_role) {
        this.user_role = user_role;
    }

}