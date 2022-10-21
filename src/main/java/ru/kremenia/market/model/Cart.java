package ru.kremenia.market.model;

import lombok.Data;
import ru.kremenia.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    // Метод поиска Items
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // Метод добавления в корзину
    public void add(Product product) {
        for (CartItem item : items) {
            if(product.getId().equals(item.getProductId())){
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getTitle(),
                product.getId(),
                1,
                product.getPrice(),
                product.getPrice()));
        recalculate();
    }

    public void remove(Long productId){
        if(items.removeIf(item ->item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear(){
        items.clear();
        totalPrice = 0;
    }

    // Метод пересчёта стоимости
    private void recalculate() {
        totalPrice = 0;
        for (CartItem item: items) {
            totalPrice += item.getPrice();
        }
    }
}
