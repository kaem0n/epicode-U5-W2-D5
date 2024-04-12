package kaem0n.u5w2d5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record DeviceDTO(@NotEmpty(message = "Type field must not be empty")
                        @Pattern(regexp = "Computer|Server|Surveillance", message = "Invalid type (available: 'Computer', 'Server', 'Surveillance').")
                        String type) {
}
