package ms_filazero.ms_orders.domain.repository;

import ms_filazero.ms_orders.domain.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository {

    OrderItem save(OrderItem item);

    List<OrderItem> findByOrderId(UUID orderId);
}
