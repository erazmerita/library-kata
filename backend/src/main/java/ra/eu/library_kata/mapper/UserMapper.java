package ra.eu.library_kata.mapper;

import org.mapstruct.Mapper;
import ra.eu.library_kata.dto.UserDto;
import ra.eu.library_kata.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends DtoMapper<UserDto, UserEntity> {
}
