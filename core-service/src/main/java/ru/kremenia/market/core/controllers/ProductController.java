package ru.kremenia.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kremenia.market.api.ProductDto;
import ru.kremenia.market.api.ResourceNotFoundException;
import ru.kremenia.market.core.converters.ProductConverter;
import ru.kremenia.market.core.entities.Product;
import ru.kremenia.market.core.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id" + id));
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product p = productService.createNewProduct(productDto);
        return productConverter.entityToDto(p);
    }

    @DeleteMapping ("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
