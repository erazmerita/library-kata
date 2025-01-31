package ra.eu.library_kata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "User")
public class UserDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
