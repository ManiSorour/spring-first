package org.example.springproductmanagment.repository;

import org.example.springproductmanagment.model.role.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringRepository extends JpaRepository<User,Integer> {



}
