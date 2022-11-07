package ru.kremenia.market.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kremenia.market.api.ProductDto;
import ru.kremenia.market.core.entities.Product;
import ru.kremenia.market.core.repositories.ProductRepository;
import ru.kremenia.market.core.soap.Productxml;

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

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        productRepository.save(product);
        return product;
    }
}
