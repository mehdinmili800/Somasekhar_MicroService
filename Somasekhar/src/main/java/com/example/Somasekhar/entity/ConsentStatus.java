package com.example.Somasekhar.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "consent_statuses")
public class ConsentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dealershipIdentifier;
    private String customerIdentifier;
    private String status;
    private String consentMessage;
    private String eventType;
}
