package ru.kremenia.market.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kremenia.market.api.CartDto;
import ru.kremenia.market.api.CartItemDto;
import ru.kremenia.market.core.entities.Order;
import ru.kremenia.market.core.entities.Product;
import ru.kremenia.market.core.integration.CartServiceIntegration;
import ru.kremenia.market.core.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(10L);
        cartItemDto.setProductTitle("Kiwi");
        cartItemDto.setQuantity(2);
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(37));
        cartItemDto.setPrice(BigDecimal.valueOf(18.5));

        List<CartItemDto> items = new ArrayList<>();

        items.add(cartItemDto);
        cartDto.setItems(items);
        cartDto.setTotalPrice(BigDecimal.valueOf(37));
        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCart();
        Product product = new Product(10L, "Kiwi", BigDecimal.valueOf(18.5));
        Mockito.doReturn(Optional.of(product)).when(productService).findById(10L);
        Order order = orderService.createOrder("Bob");
        Assertions.assertEquals(order.getTotalPrice(), BigDecimal.valueOf(37));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
