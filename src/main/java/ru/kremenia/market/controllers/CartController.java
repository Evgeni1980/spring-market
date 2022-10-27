package ru.kremenia.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kremenia.market.model.Cart;
import ru.kremenia.market.service.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public  void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

}
