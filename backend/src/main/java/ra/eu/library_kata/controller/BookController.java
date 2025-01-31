package ra.eu.library_kata.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.eu.library_kata.dto.BookDto;
import ra.eu.library_kata.service.BookService;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@Valid @RequestBody BookDto bookDto) {
        Long bookId = bookService.create(bookDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookId);
    }
}
