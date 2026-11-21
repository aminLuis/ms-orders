package ms_filazero.ms_orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateOrderStatusRequest {

    @NotBlank(message = "status is required")
    @Pattern(
        regexp = "PENDING|CONFIRMED|CANCELLED|COMPLETED",
        message = "status must be one of: PENDING, CONFIRMED, CANCELLED, COMPLETED"
    )
    private String status;

    public UpdateOrderStatusRequest() {
    }

    public UpdateOrderStatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
