package ra.eu.library_kata.service;

import ra.eu.library_kata.dto.BookDto;
import ra.eu.library_kata.dto.BorrowBookDto;

import java.util.List;

public interface LibraryService {

    boolean borrowBook(BorrowBookDto borrowBookDto);

    boolean returnBook(BorrowBookDto borrowBookDto);

    List<BookDto> getUserBorrowedBooks(Long userId);
}
