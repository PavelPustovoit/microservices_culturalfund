package com.culturalfund.serviceidentity.service;

import com.culturalfund.serviceidentity.repo.UserRepo;
import com.culturalfund.serviceidentity.repo.model.User;
import com.culturalfund.serviceidentity.repo.model.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    public User fetchById(long id) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isEmpty()) throw new IllegalArgumentException("Car not found");
        else return maybeUser.get();
    }

    public long create(String name, String surname, String email, String password, UserType userType, LocalDate registeredAt) {

        final User user = new User(name, surname, email, password, userType, registeredAt);
        final User savedUser = userRepo.save(user);

        return savedUser.getId();
    }

    public void update(Long id, String name, String surname,
                       String email, String password, UserType userType) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");

        final User user = maybeUser.get();
        if (name != null && !name.isBlank()) user.setName(name);
        if (surname != null && !surname.isBlank()) user.setSurname(surname);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (password != null && !password.isBlank()) user.setPassword(password);
        if (userType != null) user.setUserType(userType);
        userRepo.save(user);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
