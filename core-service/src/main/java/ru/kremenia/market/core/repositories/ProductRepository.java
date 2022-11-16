package ru.kremenia.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kremenia.market.core.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    static List<Product> findProductByPriceGreaterThanEqual(BigDecimal min) {
        return null;
    }

    static List<Product> findProductByPriceLessThanEqual(BigDecimal max) {
        return null;
    }

    static List<Product> findProductByPriceGreaterThanEqualAndPriceGreaterThanEqual(BigDecimal min, BigDecimal max) {
        return null;
    }

}
