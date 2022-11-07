package ru.kremenia.market.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kremenia.market.api.CartDto;
import ru.kremenia.market.api.ResourceNotFoundException;
import ru.kremenia.market.core.entities.Order;
import ru.kremenia.market.core.entities.OrderItem;
import ru.kremenia.market.core.entities.User;
import ru.kremenia.market.core.integration.CartServiceIntegration;
import ru.kremenia.market.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableTransactionManagement(proxyTargetClass = true)
public class OrderService {
    private final ProductService productService;
    private final OrderRepository repository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(User user) {
        CartDto cart = cartServiceIntegration.getCart().orElseThrow(()-> new ResourceNotFoundException("Cart not found"));
        Order order = new Order();
        List<OrderItem> orderItem = cart.getItems().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getProductId()).get(),
                order,
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getPricePerProduct())).collect(Collectors.toList());
        order.setUser(user);
        order.setItems(orderItem);
        order.setTotalPrice(countPrice(orderItem));
        repository.save(order);
    }

    private Integer countPrice(List<OrderItem> orderItems){
        Integer total = 0;
        for(OrderItem item: orderItems){
            total+=item.getTotalPrice();
        }
        return total;
    }

}
