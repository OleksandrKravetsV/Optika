package com.example.optika.controller;

import com.example.optika.model.Role;
import com.example.optika.model.User;
import com.example.optika.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
                        //    @RequestParam("password2") String passwordConfirm, BindingResult bindingResult,
    public String addUser( User user, Model model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "user exists!");
            return "registration";
        }

//new
//        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
//            model.addAttribute("passwordError", "Passwords are different!");
//        }
//
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
//
//            model.mergeAttributes(errors);
//
//            return "registration";
//        }

        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
