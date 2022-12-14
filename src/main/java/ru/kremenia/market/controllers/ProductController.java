package ru.kremenia.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kremenia.market.dtos.ProductDto;
import ru.kremenia.market.entities.Product;
import ru.kremenia.market.exceptions.ResourceNotFoundException;
import ru.kremenia.market.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
// Начальный адрес
@RequestMapping("/api/v1/products")
// Обязателен при внедрении, для всех обязательных полей создается конструктор
@RequiredArgsConstructor
public class ProductController {
    // Внедряем ProductService
    private final ProductService productService;

    // @GetMapping указывает что для доступа к методу findAllProducts() нужно получит get запрос
    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден:" + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

    @DeleteMapping ("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
