package ru.kremenia.market.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kremenia.market.entities.Order;
import ru.kremenia.market.entities.OrderItem;
import ru.kremenia.market.entities.User;
import ru.kremenia.market.model.Cart;
import ru.kremenia.market.repositories.OrderItemRepository;
import ru.kremenia.market.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableTransactionManagement(proxyTargetClass = true)
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderRepository repository;
    private final OrderItemRepository itemRepository;

    @Transactional
    public void createOrder(User user) {
        Cart cart = cartService.getCurrentCart();
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
        for (OrderItem item: orderItem){
            itemRepository.save(item);
        }

    }

    private Integer countPrice(List<OrderItem> orderItems){
        Integer total = 0;
        for(OrderItem item: orderItems){
            total+=item.getTotalPrice();
        }
        return total;
    }

}
