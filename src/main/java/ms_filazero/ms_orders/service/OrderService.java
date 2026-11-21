package ms_filazero.ms_orders.service;

import ms_filazero.ms_orders.domain.model.Order;
import ms_filazero.ms_orders.domain.model.OrderItem;
import ms_filazero.ms_orders.domain.repository.OrderItemRepository;
import ms_filazero.ms_orders.domain.repository.OrderRepository;
import ms_filazero.ms_orders.dto.CreateOrderRequest;
import ms_filazero.ms_orders.dto.OrderResponse;
import ms_filazero.ms_orders.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = orderMapper.toDomain(request);
        List<OrderItem> items = orderMapper.toOrderItems(request, order.getId());

        Order savedOrder = orderRepository.save(order);
        List<OrderItem> savedItems = items.stream()
                .map(orderItemRepository::save)
                .collect(Collectors.toList());

        return orderMapper.toResponse(savedOrder, savedItems);
    }

    public OrderResponse findById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));

        List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
        return orderMapper.toResponse(order, items);
    }

    public List<OrderResponse> findByUserId(UUID userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(order -> {
                    List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
                    return orderMapper.toResponse(order, items);
                })
                .collect(Collectors.toList());
    }

    public OrderResponse updateStatus(UUID id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));

        order.setStatus(status);
        Order updated = orderRepository.save(order);

        List<OrderItem> items = orderItemRepository.findByOrderId(updated.getId());
        return orderMapper.toResponse(updated, items);
    }
}
