package com.orderfy.auth_service.Restaurant;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false,length = 36,columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false,length = 14,columnDefinition = "CHAR(14)")
    private String cnpj;

}