package ru.ismailov.microservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ismailov.microservice.model.Subscription;
import ru.ismailov.microservice.model.TopSubscriptionDto;
import ru.ismailov.microservice.repository.SubscriptionRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    public Subscription addSubscription(Long userId, @NonNull Subscription subscription) {
        log.info("Adding subscription: {} for user with Isd {}", subscription.getName(), userId);
        return subscriptionRepository.save(
                subscription.setUser(
                        userService.getUserById(userId))
        );
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        log.info("Getting subscriptions by user with Id: {}", userId);
        var list = subscriptionRepository.findByUserId(userId);
        if (list.isEmpty()) {
            log.info("No subscriptions found for user with Id: {}, or user is not exist!", userId);
        }
        return list;
    }

    public void deleteSubscription(Long subscriptionId) {
        log.info("Deleting subscription with Id: {}.", subscriptionId);
        try {
            subscriptionRepository.deleteById(subscriptionId);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Subscription with Id: " + subscriptionId + " not found.");
        }
    }

    public List<TopSubscriptionDto> findTopSubscriptions() {
        log.info("Getting top subscriptions");
        return subscriptionRepository.findTopSubscriptions(PageRequest.of(0,3));
    }
}
