package org.example.springproductmanagment.controller;

import org.example.springproductmanagment.model.product.Product;
import org.example.springproductmanagment.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.example.springproductmanagment.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public List<Product> getAll() {
        return warehouseService.getAllProducts();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product ,@AuthenticationPrincipal User performedBy){

    warehouseService.addProduct(product,performedBy);

    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product , @AuthenticationPrincipal User performedBy){

        warehouseService.updateProduct(  product.getId(), product.getName(), product.getCode(), product.getCategory(),
                product.getPurchasePrice(), product.getSellPrice(), product.getMinStockLevel(),
                performedBy);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam int id,@AuthenticationPrincipal User performedBy){

        warehouseService.deleteProduct(id,performedBy);

    }
    @PostMapping("/products/sell")
    @ResponseStatus(HttpStatus.OK)
    public void sell(@PathVariable int id , int quantity ,@AuthenticationPrincipal User performedBy){
        warehouseService.sellProduct(id , quantity , performedBy);


    }

    @PostMapping("/products/purchase")
    @ResponseStatus(HttpStatus.OK)
    public void purchase(@PathVariable int id , int quantity , @AuthenticationPrincipal User performedBy){
        warehouseService.sellProduct(id , quantity , performedBy);


    }


}
