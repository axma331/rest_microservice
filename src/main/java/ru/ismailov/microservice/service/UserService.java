package ru.ismailov.microservice.service;

import io.micrometer.common.lang.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ismailov.microservice.model.User;
import ru.ismailov.microservice.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User addUser(User user) {
        logger.info("User {} created.", user.getName());
        return repository.save(user);
    }

    public User getUserById(Long id) {
        logger.info("Getting user with Id: {}.", id);
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with Id: " + id + " not found."));
    }

    public List<User> getAllUsers() {
        logger.info("Getting all users.");
        return repository.findAll();
    }

    public User updateUser(Long id, @NonNull User updatedData) {
        logger.info("Updating user with Id: {}. New users data: {}", id, updatedData);
        return repository.save(
                repository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("User with Id: " + id + " not found."))
                        .setName(updatedData.getName())
                        .setEmail(updatedData.getEmail())
        );
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user with Id: {}.", id);
        repository.deleteById(id);
    }
}
