package ra.eu.library_kata.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {

    @NotEmpty(message = "Title cannot be null or empty")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @NotEmpty(message = "Author cannot be null or empty")
    @Size(min = 3, max = 255, message = "Author must be between 3 and 255 characters")
    private String author;

    @NotEmpty(message = "ISBN cannot be null or empty")
    @Size(min = 14, max = 14, message = "ISBN must be 14 characters")
    private String isbn;
}
