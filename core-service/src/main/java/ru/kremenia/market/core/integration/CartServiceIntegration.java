package ru.kremenia.market.core.integration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kremenia.market.api.CartDto;

@Component
@AllArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
//                .onStatus(httpStatus -> httpStatus.value()== HttpStatus.NOT_FOUND.value(),
//                        clientResponse -> Mono.error(new ResourceNotFoundException("Product not found")))
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear() {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
