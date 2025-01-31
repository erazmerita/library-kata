package ra.eu.library_kata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ra.eu.library_kata.dto.BorrowBookDto;
import ra.eu.library_kata.entity.BookEntity;
import ra.eu.library_kata.entity.BorrowedBookEntity;
import ra.eu.library_kata.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface BorrowBookMapper extends DtoMapper<BorrowBookDto, BorrowedBookEntity> {

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "book", source = "bookId", qualifiedByName = "mapBook")
    BorrowedBookEntity dtoToEntity(BorrowBookDto dto);

    @Named("mapUser")
    default UserEntity mapUser(Long userId) {
        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;
    }

    @Named("mapBook")
    default BookEntity mapBook(Long bookId) {
        if (bookId == null) return null;
        BookEntity book = new BookEntity();
        book.setId(bookId);
        return book;
    }
}
