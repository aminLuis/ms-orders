package ms_filazero.ms_orders.infrastructure.persistence.repository;

import ms_filazero.ms_orders.domain.model.OrderItem;
import ms_filazero.ms_orders.domain.repository.OrderItemRepository;
import ms_filazero.ms_orders.mapper.OrderItemPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final JpaOrderItemRepository jpaOrderItemRepository;
    private final OrderItemPersistenceMapper orderItemPersistenceMapper;

    public OrderItemRepositoryImpl(JpaOrderItemRepository jpaOrderItemRepository,
                                   OrderItemPersistenceMapper orderItemPersistenceMapper) {
        this.jpaOrderItemRepository = jpaOrderItemRepository;
        this.orderItemPersistenceMapper = orderItemPersistenceMapper;
    }

    @Override
    public OrderItem save(OrderItem item) {
        var entity = orderItemPersistenceMapper.toEntity(item);
        var saved = jpaOrderItemRepository.save(entity);
        return orderItemPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<OrderItem> findByOrderId(UUID orderId) {
        return jpaOrderItemRepository.findByOrderId(orderId)
                .stream()
                .map(orderItemPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
