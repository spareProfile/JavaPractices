package ru.baranova.NauJava.Service;

import ru.baranova.NauJava.entity.Role;
import ru.baranova.NauJava.entity.User;


public interface UserService {
    void createUser(String userName, String email, String roleName);

    void createUser(User user);

    User getUserByName(String name);
}
