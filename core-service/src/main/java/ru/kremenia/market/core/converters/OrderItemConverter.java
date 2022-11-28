package ru.kremenia.market.core.converters;

import org.springframework.stereotype.Component;
import ru.kremenia.market.api.OrderItemDto;
import ru.kremenia.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getProduct().getId(), o.getProduct().getTitle(), o.getQuantity(), o.getTotalPrice(), o.getPrice());
    }
}
