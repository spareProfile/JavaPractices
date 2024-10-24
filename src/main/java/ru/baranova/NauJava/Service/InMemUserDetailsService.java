package ru.baranova.NauJava.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ru.baranova.NauJava.entity.Role;
import ru.baranova.NauJava.entity.User;

@Component
public class InMemUserDetailsService implements UserDetailsService{
    private UserServiceImpl userService;

    public InMemUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myAppUser = userService.getUserByName(username);
        if (myAppUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
       List lis = new ArrayList<String>();
       lis.add("ROle");

        return new org.springframework.security.core.userdetails.User(myAppUser.getUserName(), myAppUser.getPassword(), mapRoles(myAppUser));

    }

    private List<GrantedAuthority> mapRoles(User appUser){
        List lis = new ArrayList<String>();
        SimpleGrantedAuthority GrantedAuthority = new SimpleGrantedAuthority("ROLE_" + appUser.getRoles());
        lis.add(GrantedAuthority);
        return lis;
    }
    
}
