package ra.eu.library_kata.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BorrowBookDto {

    @NotNull(message = "User id must be populated")
    private Long userId;

    @NotNull(message = "Book id must be populated")
    private Long bookId;
}
