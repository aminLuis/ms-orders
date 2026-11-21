package ms_filazero.ms_orders.infrastructure.persistence.repository;

import ms_filazero.ms_orders.domain.model.Order;
import ms_filazero.ms_orders.domain.repository.OrderRepository;
import ms_filazero.ms_orders.mapper.OrderPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository,
                               OrderPersistenceMapper orderPersistenceMapper) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.orderPersistenceMapper = orderPersistenceMapper;
    }

    @Override
    public Order save(Order order) {
        var entity = orderPersistenceMapper.toEntity(order);
        var saved = jpaOrderRepository.save(entity);
        return orderPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaOrderRepository.findById(id)
                .map(orderPersistenceMapper::toDomain);
    }

    @Override
    public List<Order> findByUserId(UUID userId) {
        return jpaOrderRepository.findByUserId(userId)
                .stream()
                .map(orderPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
