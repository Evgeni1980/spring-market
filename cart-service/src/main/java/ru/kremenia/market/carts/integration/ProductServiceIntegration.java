package ru.kremenia.market.carts.integration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kremenia.market.api.ProductDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market/api/v1/products/" + id, ProductDto.class));
    }
}
