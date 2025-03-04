package ru.ismailov.microservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ismailov.microservice.model.Subscription;
import ru.ismailov.microservice.model.TopSubscriptionDto;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    @Query("select new ru.ismailov.microservice.model.TopSubscriptionDto(s.name , count(s)) " +
            "from Subscription s " +
            "group by s.name " +
            "order by count(s) desc")
    List<TopSubscriptionDto> findTopSubscriptions(Pageable pageable);

}
