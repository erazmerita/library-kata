package ra.eu.library_kata.service;

import ra.eu.library_kata.dto.BookDto;

public interface BookService {

    Long create(BookDto bookDto);
}
