package ra.eu.library_kata.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ra.eu.library_kata.constants.ResponseCode;
import ra.eu.library_kata.dto.UserDto;
import ra.eu.library_kata.entity.UserEntity;
import ra.eu.library_kata.expection.Custom400RuntimeException;
import ra.eu.library_kata.mapper.UserMapper;
import ra.eu.library_kata.repository.UserRepository;
import ra.eu.library_kata.service.UserService;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Long create(UserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            log.info("User with email {} already exist", userDto.getEmail());
            throw new Custom400RuntimeException(ResponseCode.USER_ALREADY_EXISTS);
        }

        UserEntity newUser = userMapper.dtoToEntity(userDto);
        UserEntity savedUser = userRepository.save(newUser);

        return savedUser.getId();
    }
}
