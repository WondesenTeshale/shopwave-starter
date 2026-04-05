// Name: Wendesen Teshale
// Student Number: ATE/4671/14
package com.shopwave.repository;

import com.shopwave.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
