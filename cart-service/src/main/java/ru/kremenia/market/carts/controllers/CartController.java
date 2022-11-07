package ru.kremenia.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kremenia.market.api.CartDto;
import ru.kremenia.market.carts.convertes.CartConverter;
import ru.kremenia.market.carts.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public  void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

}
