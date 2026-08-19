package org.example.springproductmanagment.repository;

import org.example.springproductmanagment.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpringRepository extends JpaRepository<Product,Integer> {
}
