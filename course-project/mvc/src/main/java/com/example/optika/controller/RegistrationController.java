package com.example.optika.controller;

import com.example.optika.model.Role;
import com.example.optika.model.User;
import com.example.optika.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser( @Valid User user, BindingResult result, Model model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (result.hasErrors()) {
            return "registration";
        }
        if (userFromDB != null) {
            model.addAttribute("message", "user exists!");
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
