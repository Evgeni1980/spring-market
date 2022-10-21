package ru.kremenia.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kremenia.market.entities.Product;
import ru.kremenia.market.exceptions.ResourceNotFoundException;
import ru.kremenia.market.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удаётся добавить продукт с id:" + productId + "в корзину. Продукт не найден"));
        tempCart.add(product);
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }

    public void clear() {
        tempCart.clear();
    }

}
