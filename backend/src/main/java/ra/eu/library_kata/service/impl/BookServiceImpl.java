package ra.eu.library_kata.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ra.eu.library_kata.constants.ResponseCode;
import ra.eu.library_kata.dto.BookDto;
import ra.eu.library_kata.entity.BookEntity;
import ra.eu.library_kata.expection.Custom400RuntimeException;
import ra.eu.library_kata.mapper.BookMapper;
import ra.eu.library_kata.repository.BookRepository;
import ra.eu.library_kata.service.BookService;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Long create(BookDto bookDto) {
        Optional<BookEntity> optionalBook = bookRepository.findByIsbn(bookDto.getIsbn());
        if (optionalBook.isPresent()) {
            log.info("Book with ISBN {} already exist", bookDto.getIsbn());
            throw new Custom400RuntimeException(ResponseCode.BOOK_ALREADY_EXISTS);
        }

        BookEntity newBook = bookMapper.dtoToEntity(bookDto);
        BookEntity savedBook = bookRepository.save(newBook);

        return savedBook.getId();
    }
}
