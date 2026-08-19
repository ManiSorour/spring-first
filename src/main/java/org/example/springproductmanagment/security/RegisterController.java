package org.example.springproductmanagment.security;

import org.example.springproductmanagment.model.role.Admin;
import org.example.springproductmanagment.model.role.Inspector;
import org.example.springproductmanagment.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.example.springproductmanagment.repository.UserSpringRepository;


@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private UserSpringRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;


@PostMapping
@PreAuthorize("hasRole('ADMIN')")
@ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestParam String username ,@RequestParam String password ,@RequestParam String role){

        String hashedPassword = passwordEncoder.encode(password);

        User newUser = switch (role) {
            case "ADMIN" -> new Admin(0, username, hashedPassword);
            case "INSPECTOR" -> new Inspector(0, username, hashedPassword);
            default -> null;
        };


        if (newUser==null){
            throw new IllegalArgumentException("user not found");
        }



        repository.save(newUser);


    }


















}
