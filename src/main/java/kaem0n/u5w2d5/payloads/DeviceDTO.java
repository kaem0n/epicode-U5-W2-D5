package kaem0n.u5w2d5.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeviceDTO(@NotEmpty(message = "Type field must not be empty")
                        @Size(min = 3, max = 20, message = "Type field length must be between 3 and 20 characters.")
                        String type,
                        @NotEmpty(message = "Status field must not be empty")
                        @Size(min = 3, max = 20, message = "Status field length must be between 3 and 20 characters.")
                        String status,
                        @Min(value = 1, message = "Invalid ID value.")
                        @NotNull(message = "Author ID field must not be null.")
                        long employeeId) {
}
