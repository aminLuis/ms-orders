package ms_filazero.ms_orders.mapper;

import ms_filazero.ms_orders.domain.model.OrderItem;
import ms_filazero.ms_orders.dto.OrderItemRequest;
import ms_filazero.ms_orders.dto.OrderItemResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemMapper {

    /**
     * Creates an OrderItem domain object from an OrderItemRequest.
     * Generates a new UUID for the item and assigns the given orderId.
     */
    public OrderItem toDomain(OrderItemRequest request, UUID orderId) {
        return new OrderItem(
                UUID.randomUUID(),
                orderId,
                request.getTicketId(),
                request.getPrice()
        );
    }

    public OrderItemResponse toResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getId(),
                item.getOrderId(),
                item.getTicketId(),
                item.getPrice()
        );
    }
}
