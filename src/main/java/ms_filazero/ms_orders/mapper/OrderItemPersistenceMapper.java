package ms_filazero.ms_orders.mapper;

import ms_filazero.ms_orders.domain.model.OrderItem;
import ms_filazero.ms_orders.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemPersistenceMapper {

    public OrderItemEntity toEntity(OrderItem item) {
        return new OrderItemEntity(
                item.getId(),
                item.getOrderId(),
                item.getTicketId(),
                item.getPrice()
        );
    }

    public OrderItem toDomain(OrderItemEntity entity) {
        return new OrderItem(
                entity.getId(),
                entity.getOrderId(),
                entity.getTicketId(),
                entity.getPrice()
        );
    }
}
