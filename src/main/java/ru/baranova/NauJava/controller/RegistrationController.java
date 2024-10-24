package ru.baranova.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import ru.baranova.NauJava.entity.User;
import ru.baranova.NauJava.Service.UserService;
import ru.baranova.NauJava.entity.Role;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String adduser(User user, Model model){
        try{userService.createUser(user);
            return "redirect:/login";
        }
        catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
    @GetMapping("/logout")
    public String logout( Model model){
        return "redirect:/login?logout";
    }

}