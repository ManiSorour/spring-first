package org.example.springproductmanagment.service;

import org.example.springproductmanagment.model.product.Product;
import org.example.springproductmanagment.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.springproductmanagment.repository.ProductSpringRepository;

import java.util.List;

@Service
public class WarehouseService {


    @Autowired
    private ProductSpringRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void addProduct(Product product, User performedBy) {
        if (!performedBy.canEditStock()) {
            throw new SecurityException("only admin can add product");
        }
        repository.save(product);
    }

    public void updateProduct(int productId, String name, String code, String category, double purchasePrice, double sellPrice, int minStockLevel, User performedBy) {
        if (!performedBy.canEditStock()) {
            throw new SecurityException("only admin can add product");
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("no product with this id found"));
        product.setName(name);
        product.setCode(code);
        product.setCategory(category);
        product.setPurchasePrice(purchasePrice);
        product.setSellPrice(sellPrice);
        product.setMinStockLevel(minStockLevel);

        repository.save(product);

    }

    public void deleteProduct(int productId, User performedBy) {
        if (!performedBy.canEditStock()) {
            throw new SecurityException("این کاربر اجازه حذف را ندارد");
        }
        repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("کالایی با این شناسه پیدا نشد"));
        repository.deleteById(productId);
    }

    public void sellProduct(int productId, int quantity, User performedBy) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("product with this id not found"));

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("there is no product right now");
        }

        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);
    }

    public void purchaseProduct(int productId, int quantity, User performedBy) {
        if (!performedBy.canEditStock()) {
            throw new SecurityException("این کاربر اجازه ثبت موجودی را ندارد");
        }
        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("کالایی با این شناسه پیدا نشد"));

        product.setQuantity(product.getQuantity() + quantity);
        repository.save(product);
    }

}
