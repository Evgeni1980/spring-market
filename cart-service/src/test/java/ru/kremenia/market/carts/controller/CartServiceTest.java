package ru.kremenia.market.carts.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kremenia.market.api.ProductDto;
import ru.kremenia.market.carts.integration.ProductServiceIntegration;
import ru.kremenia.market.carts.model.Cart;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceTest {
    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void addTest(){
        ProductDto productDto = new ProductDto(4l, "Milk", BigDecimal.valueOf(40));
        Cart cart = new Cart();
        Mockito.doReturn(productDto).when(productServiceIntegration).getProductById(4l);
        cart.add(productDto);
        Assertions.assertEquals(1, cart.getItems().size());
    }
}
