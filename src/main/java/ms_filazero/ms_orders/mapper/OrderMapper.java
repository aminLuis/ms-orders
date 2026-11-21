package ms_filazero.ms_orders.mapper;

import ms_filazero.ms_orders.domain.model.Order;
import ms_filazero.ms_orders.domain.model.OrderItem;
import ms_filazero.ms_orders.dto.CreateOrderRequest;
import ms_filazero.ms_orders.dto.OrderItemResponse;
import ms_filazero.ms_orders.dto.OrderResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    /**
     * Creates an Order domain object from a CreateOrderRequest.
     * Generates a new UUID, sets status to PENDING, calculates totalAmount
     * as the sum of all item prices, and sets createdAt to now.
     */
    public Order toDomain(CreateOrderRequest request) {
        UUID orderId = UUID.randomUUID();

        BigDecimal totalAmount = request.getItems().stream()
                .map(item -> item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Order(
                orderId,
                request.getUserId(),
                "PENDING",
                totalAmount,
                LocalDateTime.now()
        );
    }

    /**
     * Maps the OrderItem requests to domain objects, binding each one to the given orderId.
     */
    public List<OrderItem> toOrderItems(CreateOrderRequest request, UUID orderId) {
        return request.getItems().stream()
                .map(itemRequest -> orderItemMapper.toDomain(itemRequest, orderId))
                .collect(Collectors.toList());
    }

    public OrderResponse toResponse(Order order, List<OrderItem> items) {
        List<OrderItemResponse> itemResponses = items.stream()
                .map(orderItemMapper::toResponse)
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                itemResponses
        );
    }
}
