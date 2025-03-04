package ru.ismailov.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ismailov.microservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
