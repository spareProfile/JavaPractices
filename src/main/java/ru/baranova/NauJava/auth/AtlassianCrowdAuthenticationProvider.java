package ru.baranova.NauJava.auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AtlassianCrowdAuthenticationProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        User user = callMyAuthRestService(username, password);
        if (user == null)
        {
        throw new AuthenticationServiceException("user not found");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private User callMyAuthRestService(String username, String password){
        User user = new User(username, password, List.of(new
        SimpleGrantedAuthority("USER")));
        return user;
    }
}