package ru.kremenia.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kremenia.market.entities.Product;
import ru.kremenia.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // Для всех обязательных полей создается конструктор
public class ProductService {
    // Внедрение репозитория продуктов
    private final ProductRepository productRepository;

    // Метод возвращающий весь список продуктов
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Метод возвращающий продукт по id
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Метод удаления по id
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
