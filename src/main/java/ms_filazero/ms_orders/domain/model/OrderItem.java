package ms_filazero.ms_orders.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {

    private UUID id;
    private UUID orderId;
    private UUID ticketId;
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(UUID id, UUID orderId, UUID ticketId, BigDecimal price) {
        this.id = id;
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
