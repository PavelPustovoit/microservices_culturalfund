package com.culturalfund.serviceidentity.api;

import com.culturalfund.serviceidentity.dto.UserDto;
import com.culturalfund.serviceidentity.repo.model.User;
import com.culturalfund.serviceidentity.repo.model.UserType;
import com.culturalfund.serviceidentity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> index() {
        final List<User> users = userService.fetchAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable long id) {
        try {
            final User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDto userDto) {
        final String name = userDto.getName();
        final String surname = userDto.getSurname();
        final String email = userDto.getEmail();
        final String password = userDto.getPassword();
        final UserType userType = userDto.getUserType();
        final LocalDate registeredAt = LocalDate.now();

        final long id = userService.create(name, surname, email, password, userType, registeredAt);

        final String location = String.format("/users/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody UserDto userDto) {
        final String name = userDto.getName();
        final String surname = userDto.getSurname();
        final String email = userDto.getEmail();
        final String password = userDto.getPassword();
        final UserType userType = userDto.getUserType();

        try {
            userService.update(id, name, surname, email, password, userType);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
