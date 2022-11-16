package ru.kremenia.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kremenia.market.core.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
