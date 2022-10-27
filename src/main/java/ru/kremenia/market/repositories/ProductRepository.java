package ru.kremenia.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kremenia.market.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
