package ru.baranova.NauJava;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.entity.User;
import ru.baranova.NauJava.entity.Role;
import ru.baranova.NauJava.entity.Folder;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.repository.UserRepo;
import ru.baranova.NauJava.repository.RoleRepo;
import ru.baranova.NauJava.repository.FolderRepo;
import ru.baranova.NauJava.repository.FileMetaRepo;
import ru.baranova.NauJava.entity.FileMeta;

import ru.baranova.NauJava.dao.FileCustomRepoImpl;
import ru.baranova.NauJava.Service.FileService;
import ru.baranova.NauJava.Service.UserService;

@SpringBootTest
public class UserTest {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final UserService userService;

    @Autowired
    UserTest(RoleRepo roleRepo, UserRepo userRepo, UserService userService) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        
        this.userService = userService;
    }

    Role getDefaultRole() {
        List<Role> roles = roleRepo.findByroleName("default");
        Role role;
        if (roles.isEmpty()) { // create default role
            role = new Role("default");
            roleRepo.save(role);
        }
        else {
            role = roles.get(0);
        }
        return role;
    }

    // repository test
    @Test
    void testRepositories_5section() {
        Role role = getDefaultRole();
        String name = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString() + "@gmail.ru";
        User user = new User(name, email, role); // create User Username, password, role
        user.setPassword("123");
        userRepo.save(user);
        
        // test found User
        User foundUser = userRepo.findByuserName(name).get(0);
        Assertions.assertEquals(name, foundUser.getUserName());
       
        // test create User
        String name2 = UUID.randomUUID().toString();
        String email2 = UUID.randomUUID().toString() + "@gmail.ru";
        String roleName = "USER";

        userService.createUser(name2, email2, roleName);

        User foundUser2 = userRepo.findByuserName(name2).get(0);
        Assertions.assertEquals(name2, foundUser2.getUserName());
        
    }
}