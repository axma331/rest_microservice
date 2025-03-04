package ru.ismailov.microservice.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,  unique = true)
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Subscription> subscriptions;
}
