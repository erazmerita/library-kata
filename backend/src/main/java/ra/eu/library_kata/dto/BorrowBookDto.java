package ra.eu.library_kata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "BorrowBook")
public class BorrowBookDto {

    @NotNull(message = "User id must be populated")
    private Long userId;

    @NotNull(message = "Book id must be populated")
    private Long bookId;
}
