package ra.eu.library_kata.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.eu.library_kata.dto.UserDto;
import ra.eu.library_kata.service.UserService;

@Tag(name = "User")
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@Valid @RequestBody UserDto userDto) {
        Long userId = userService.create(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userId);
    }
}
