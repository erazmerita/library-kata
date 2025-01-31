package ra.eu.library_kata.service;

import ra.eu.library_kata.dto.UserDto;

public interface UserService {

    Long create(UserDto userDto);
}
