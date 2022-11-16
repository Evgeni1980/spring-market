package ru.kremenia.market.core.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kremenia.market.core.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createNewOrder(@RequestHeader String username) {
//      orderService.createOrder(username);
//    }
}
