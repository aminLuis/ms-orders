package ms_filazero.ms_orders.domain.repository;

import ms_filazero.ms_orders.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findByUserId(UUID userId);
}
