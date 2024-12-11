package com.example.Somasekhar.service;


import com.example.Somasekhar.dto.ConsentRequest;
import com.example.Somasekhar.dto.ConsentResponse;

public interface ConsentService {
    ConsentResponse getConsentStatus(String dealershipIdentifier, String customerIdentifier);

    ConsentResponse createConsent(ConsentRequest request);
}

