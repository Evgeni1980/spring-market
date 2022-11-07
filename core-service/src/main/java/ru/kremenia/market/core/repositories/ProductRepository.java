package ru.kremenia.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kremenia.market.core.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
