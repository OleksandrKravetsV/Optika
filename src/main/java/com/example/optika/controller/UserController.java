package com.example.optika.controller;

import com.example.optika.model.Role;
import com.example.optika.model.User;
import com.example.optika.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
//@PreAuthorize("hasAnyRole('ADMIN','USER')")

public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user")
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "/user/userList";
    }

    @GetMapping("/editUser/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "/user/userEdit";
    }

    @PostMapping("/updateUser/{id}")
    public String userSave(
        @RequestParam String username,
        @RequestParam Map<String, String> form,
        @RequestParam("id") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
            .map(Role::name)
            .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }

}
