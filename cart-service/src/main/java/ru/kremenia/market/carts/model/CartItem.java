package ru.kremenia.market.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Long productId;

    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;


    public void changeQuantity(int delta) {
        quantity += delta;
        pricePerProduct = price.multiply(BigDecimal.valueOf(quantity));
    }
}
