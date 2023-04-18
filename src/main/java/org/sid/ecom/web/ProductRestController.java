package org.sid.ecom.web;

import lombok.AllArgsConstructor;
import org.sid.ecom.dtos.ProductDTO;
import org.sid.ecom.entities.Product;
import org.sid.ecom.repositories.ProductRepository;
import org.sid.ecom.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController @AllArgsConstructor
public class ProductRestController {
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<ProductDTO> productList(){
        return productService.listProducts();
    }

    @GetMapping(path = "/products/{id}")
    public ProductDTO getProduct(@PathVariable(name = "id") String id){
        return productService.getProduct(id);
    }

    @PostMapping(name = "/products")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    @PatchMapping(name = "/products/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable String id){
        productDTO.setId(id);
        return productService.save(productDTO);
    }

    @DeleteMapping(name = "/products/{id}")
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }
}