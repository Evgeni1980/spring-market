package ru.kremenia.market.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kremenia.market.api.CartDto;
import ru.kremenia.market.core.entities.Order;
import ru.kremenia.market.core.entities.OrderItem;
import ru.kremenia.market.core.integration.CartServiceIntegration;
import ru.kremenia.market.core.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableTransactionManagement(proxyTargetClass = true)
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String username) {
        CartDto cart = cartServiceIntegration.getCart();
        Order order = new Order();
        List<OrderItem> orderItem = cart.getItems().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getProductId()).get(),
                order,
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getPricePerProduct())).collect(Collectors.toList());
        order.setUsername(username);
        order.setItems(orderItem);
        order.setTotalPrice(countPrice(orderItem));
        orderRepository.save(order);
        cartServiceIntegration.clear();
    }

    private BigDecimal countPrice(List<OrderItem> orderItems){
        BigDecimal total = BigDecimal.valueOf(0);
        for(OrderItem item: orderItems){
            total = total.add(item.getTotalPrice());
        }
        return total;
    }

}
