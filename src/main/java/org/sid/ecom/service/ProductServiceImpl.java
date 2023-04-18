package org.sid.ecom.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.ecom.dtos.ProductDTO;
import org.sid.ecom.entities.Product;
import org.sid.ecom.mappers.CatalogMappers;
import org.sid.ecom.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional @AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CatalogMappers catalogMappers;

    @Override
    public ProductDTO save(ProductDTO productDTO){
        Product product = catalogMappers.fromProductDTO(productDTO);
        product.setId(UUID.randomUUID().toString());
        Product savedProduct = productRepository.save(product);
        return catalogMappers.fromProduct(savedProduct);
    }

    @Override
    public List<ProductDTO> listProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS =
                products.stream().map(p->catalogMappers.fromProduct(p))
                        .collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public ProductDTO getProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
        return catalogMappers.fromProduct(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = catalogMappers.fromProductDTO(productDTO);
        Product savedProduct = productRepository.save(product);
        return catalogMappers.fromProduct(savedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}