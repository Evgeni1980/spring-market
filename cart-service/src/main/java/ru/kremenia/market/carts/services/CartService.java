package ru.kremenia.market.carts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kremenia.market.api.ProductDto;
import ru.kremenia.market.carts.integration.ProductServiceIntegration;
import ru.kremenia.market.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
//    @Value("${cart-service.cart-prefix}")
//    private String cartPrefix;

    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

//    public Cart getCurrentCart(String uuid) {
//        String targetUuid = cartPrefix + uuid;
//        if (!carts.containsKey(targetUuid)) {
//            carts.put(targetUuid, new Cart());
//        }
//        return carts.get(targetUuid);
//    }

    public Cart getCurrentCart(String uuid) {
        if (!carts.containsKey(uuid)) {
            Cart cart = new Cart();
            carts.put(uuid, cart);
        }
        return carts.get(uuid);
    }

    public void addToCart(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).add(product);
    }

    public void remove(String uuid, Long productId) {
        getCurrentCart(uuid).remove(productId);
    }

    public void clearCart(String uuid) {
        getCurrentCart(uuid).clear();
    }

}
