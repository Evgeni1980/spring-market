package ru.kremenia.market.core.integration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kremenia.market.api.CartDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<CartDto> getCart() {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/market-cart/api/v1/cart", CartDto.class));
    }
}
