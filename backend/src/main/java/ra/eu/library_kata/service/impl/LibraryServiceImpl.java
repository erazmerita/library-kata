package ra.eu.library_kata.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ra.eu.library_kata.constants.ResponseCode;
import ra.eu.library_kata.dto.BookDto;
import ra.eu.library_kata.dto.BorrowBookDto;
import ra.eu.library_kata.entity.BookEntity;
import ra.eu.library_kata.entity.BorrowedBookEntity;
import ra.eu.library_kata.entity.UserEntity;
import ra.eu.library_kata.expection.Custom400RuntimeException;
import ra.eu.library_kata.expection.Custom404RuntimeException;
import ra.eu.library_kata.mapper.BookMapper;
import ra.eu.library_kata.mapper.BorrowBookMapper;
import ra.eu.library_kata.repository.BookRepository;
import ra.eu.library_kata.repository.BorrowedBookRepository;
import ra.eu.library_kata.repository.UserRepository;
import ra.eu.library_kata.service.LibraryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowedBookRepository borrowedBookRepository;
    private final BorrowBookMapper borrowBookMapper;
    private final BookMapper bookMapper;

    @Override
    public boolean borrowBook(BorrowBookDto borrowBookDto) {
        validateUserAndBook(borrowBookDto);

        Optional<BorrowedBookEntity> optionalBorrowedBook =
                borrowedBookRepository.findByUserIdAndBookIdAndReturnDateIsNull(borrowBookDto.getUserId(), borrowBookDto.getBookId());
        if (optionalBorrowedBook.isPresent()) {
            log.info("Used id {} already borrow the book with id {}", borrowBookDto.getUserId(), borrowBookDto.getBookId());
            throw new Custom400RuntimeException(ResponseCode.BOOK_IS_ALREADY_BORROWED_BY_USER);
        }

        BorrowedBookEntity borrowedBookEntity = borrowBookMapper.dtoToEntity(borrowBookDto);
        borrowedBookEntity.setBorrowDate(LocalDate.now());
        borrowedBookRepository.save(borrowedBookEntity);

        return true;
    }

    private void validateUserAndBook(BorrowBookDto borrowBookDto) {
        Optional<UserEntity> optionalUser = userRepository.findById(borrowBookDto.getUserId());
        if (optionalUser.isEmpty()) {
            log.info("User with id {} does not exist", borrowBookDto.getUserId());
            throw new Custom404RuntimeException(ResponseCode.USER_DOES_NOT_EXIST);
        }

        Optional<BookEntity> optionalBook = bookRepository.findById(borrowBookDto.getBookId());
        if (optionalBook.isEmpty()) {
            log.info("Book with id {} does not exist", borrowBookDto.getUserId());
            throw new Custom404RuntimeException(ResponseCode.BOOK_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean returnBook(BorrowBookDto borrowBookDto) {
        validateUserAndBook(borrowBookDto);

        Optional<BorrowedBookEntity> optionalBorrowedBook =
                borrowedBookRepository.findByUserIdAndBookIdAndReturnDateIsNull(borrowBookDto.getUserId(), borrowBookDto.getBookId());
        if (optionalBorrowedBook.isEmpty()) {
            log.info("Used id {} did not borrow the book with id {}", borrowBookDto.getUserId(), borrowBookDto.getBookId());
            throw new Custom400RuntimeException(ResponseCode.USER_DID_NOT_BORROWED_THE_BOOK);
        }

        BorrowedBookEntity borrowedBookEntity = optionalBorrowedBook.get();
        borrowedBookEntity.setReturnDate(LocalDate.now());
        borrowedBookRepository.save(borrowedBookEntity);

        return true;
    }

    @Override
    public List<BookDto> getUserBorrowedBooks(Long userId) {
        List<BookEntity> bookEntities = borrowedBookRepository.findBooksByUserId(userId);

        return bookEntities
                .stream()
                .map(bookMapper::entityToDto)
                .toList();
    }
}
