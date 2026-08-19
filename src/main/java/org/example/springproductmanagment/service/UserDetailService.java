package org.example.springproductmanagment.service;

import org.example.springproductmanagment.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.springproductmanagment.repository.UserSpringRepository;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserSpringRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("user with this Specifications not found"));
        return user;
    }
}
