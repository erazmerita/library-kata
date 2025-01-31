package ra.eu.library_kata.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ra.eu.library_kata.dto.BookDto;
import ra.eu.library_kata.dto.BorrowBookDto;
import ra.eu.library_kata.service.LibraryService;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/borrow")
    public ResponseEntity<Boolean> borrowBook(@Valid @RequestBody BorrowBookDto borrowBookDto) {
        boolean borrowed = libraryService.borrowBook(borrowBookDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(borrowed);
    }

    @PostMapping("/return")
    public ResponseEntity<Boolean> returnBook(@Valid @RequestBody BorrowBookDto borrowBookDto) {
        boolean borrowed = libraryService.returnBook(borrowBookDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(borrowed);
    }

    @GetMapping("/getUserBorrowedBooks/{userId}")
    public ResponseEntity<List<BookDto>> getUserBorrowedBooks(@PathVariable Long userId) {
        List<BookDto> bookDtos = libraryService.getUserBorrowedBooks(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDtos);
    }
}
