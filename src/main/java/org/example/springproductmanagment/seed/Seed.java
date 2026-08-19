package org.example.springproductmanagment.seed;

import org.example.springproductmanagment.model.role.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.example.springproductmanagment.repository.UserSpringRepository;
@Component
public class Seed implements CommandLineRunner {

    @Autowired
    private UserSpringRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count()==0){
            userRepository.save(new Admin(0, "milad", passwordEncoder.encode("2")));
            System.out.println("اولین ادمین ساخته شد!");
        }
    }
}
