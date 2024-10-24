package ru.baranova.NauJava.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ru.baranova.NauJava.dao.FileCustomRepoImpl;
import ru.baranova.NauJava.entity.File;
import ru.baranova.NauJava.entity.FileMeta;
import ru.baranova.NauJava.entity.Folder;
import ru.baranova.NauJava.entity.Role;
import ru.baranova.NauJava.entity.User;
import ru.baranova.NauJava.repository.FileRepo;
import ru.baranova.NauJava.repository.FolderRepo;
import ru.baranova.NauJava.repository.RoleRepo;
import ru.baranova.NauJava.repository.UserRepo;
import ru.baranova.NauJava.repository.FileMetaRepo;

@Service
public class UserServiceImpl implements UserService{
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(RoleRepo roleRepo, UserRepo userRepo) {
            this.roleRepo = roleRepo;
            this.userRepo = userRepo;
        
    }

    Role getRole(String roleName) {
        List<Role> roles = roleRepo.findByroleName(roleName);
        Role role;
        if (roles.isEmpty()) { // create if role does not exsists
            role = new Role(roleName);
            roleRepo.save(role);
        }
        else {
            role = roles.get(0);
        }
        return role;
    }

    public void createUser(User user){
        if (getUserByName(user.getUserName()) != null) {
            System.out.println("User with name " + user.getUserName() + " already exists");
            return;
        }
        userRepo.save(user);
    }


    public void createUser(String userName, String email, String roleName){
        if (getUserByName(userName) != null) {
            System.out.println("User with name " + userName + " already exists");
            return;
        }
        Role role = getRole(roleName);
        User user = new User(userName, email, role);
        userRepo.save(user);
    }

    public void createUser(String userName, String email, Role userrole){
        if (getUserByName(userName) != null) {
            System.out.println("User with name " + userName + " already exists");
            return;
        }
        Role role = getRole(userrole.getName());
        User user = new User(userName, email, role);
        userRepo.save(user);
    }

    public User getUserByName(String name) {
        try{
            User user = userRepo.findByuserName(name).get(0);
            return user;
        } catch (Exception e) {
            System.out.println("User witn name " + name +  " not found");
            return null;
        }
}


}