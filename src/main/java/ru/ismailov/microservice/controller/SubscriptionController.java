package ru.ismailov.microservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ismailov.microservice.model.Subscription;
import ru.ismailov.microservice.model.TopSubscriptionDto;
import ru.ismailov.microservice.service.SubscriptionService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@PathVariable(value = "id") Long userId,
                                                        @RequestBody Subscription subscription) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(subscriptionService.addSubscription(userId, subscription));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable(value = "id") Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(userId));
    }

    @GetMapping("subscriptions/top")
    public ResponseEntity<List<TopSubscriptionDto>> getSubscription() {
        return ResponseEntity.ok(subscriptionService.findTopSubscriptions());
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable(value = "id") Long subId) {
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }
}
