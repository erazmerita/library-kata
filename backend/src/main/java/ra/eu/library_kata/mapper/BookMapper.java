package ra.eu.library_kata.mapper;

import org.mapstruct.Mapper;
import ra.eu.library_kata.dto.BookDto;
import ra.eu.library_kata.entity.BookEntity;

@Mapper(componentModel = "spring")
public interface BookMapper extends DtoMapper<BookDto, BookEntity> {
}
