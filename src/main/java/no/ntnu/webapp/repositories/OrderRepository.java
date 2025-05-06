package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.Order;
import no.ntnu.webapp.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_UserIdAndOrderStatus(Long userId, OrderStatus status);
}
