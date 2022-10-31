package ru.kremenia.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kremenia.market.entities.Product;
import ru.kremenia.market.repositories.ProductRepository;
import ru.kremenia.market.soap.Productxml;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final Function<Product, Productxml> entityToSoap = p->{
        Productxml productxml = new Productxml();
        productxml.setId(p.getId());
        productxml.setTitle(p.getTitle());
        productxml.setPrice(p.getPrice());
        return productxml;
    };

    public List<Productxml> findAllXml() {
        return productRepository.findAll().stream().map(entityToSoap).collect(Collectors.toList());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
