package com.culturalfund.serviceidentity.repo;

import com.culturalfund.serviceidentity.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findBySurname(String surname);
}
