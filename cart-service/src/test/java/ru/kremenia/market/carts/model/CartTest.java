package ru.kremenia.market.carts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kremenia.market.api.ProductDto;

import java.math.BigDecimal;

@SpringBootTest
public class CartTest {
    @Spy
    private Cart cart;

    @Test
    public void addTest() {
        ProductDto productDto = new ProductDto(10l, "Banana", BigDecimal.valueOf(60), "Food");
        cart.add(productDto);
        Mockito.verify(cart).add(productDto);
        Assertions.assertEquals(1, cart.getItems().size());
    }
}
