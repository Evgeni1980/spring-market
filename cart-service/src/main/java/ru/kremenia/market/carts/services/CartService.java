package ru.kremenia.market.carts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kremenia.market.api.ProductDto;
import ru.kremenia.market.api.ResourceNotFoundException;
import ru.kremenia.market.carts.integration.ProductServiceIntegration;
import ru.kremenia.market.carts.model.Cart;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart Cart;

    @PostConstruct
    public void init() {
        Cart = new Cart();
    }

    public Cart getCurrentCart() {
        return Cart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удаётся добавить продукт с id:" + productId + "в корзину. Продукт не найден"));
        Cart.add(product);
    }

    public void remove(Long productId) {
        Cart.remove(productId);
    }

    public void clear() {
        Cart.clear();
    }

}
