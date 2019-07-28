package ru.web_str.sweater.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.web_str.sweater.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
