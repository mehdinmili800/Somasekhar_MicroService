package com.example.Somasekhar.service.impl;

import com.example.Somasekhar.dto.ConsentRequest;
import com.example.Somasekhar.dto.ConsentResponse;
import com.example.Somasekhar.entity.ConsentStatus;
import com.example.Somasekhar.repository.ConsentRepository;
import com.example.Somasekhar.service.ConsentService;
import com.example.Somasekhar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsentServiceImpl implements ConsentService {

    @Autowired
    private ConsentRepository repository;

    // retrieve
    @Override
    public ConsentResponse getConsentStatus(String dealershipIdentifier, String customerIdentifier) {
        ConsentResponse response = new ConsentResponse();
        response.setStatus("Granted");
        response.setDealershipIdentifier(dealershipIdentifier);
        response.setCustomerIdentifier(customerIdentifier);
        return response;
    }

    @Override
    public ConsentResponse createConsent(ConsentRequest request) {
        ConsentStatus consentStatus = new ConsentStatus();
        consentStatus.setDealershipIdentifier(request.getDealershipIdentifier());
        consentStatus.setCustomerIdentifier(request.getCustomerIdentifier());
        consentStatus.setStatus(Constants.STATUS_GRANTED);
        consentStatus.setConsentMessage(request.getConsentMessage());
        consentStatus.setEventType("CREATION_EVENT");

        repository.save(consentStatus);

        ConsentResponse response = new ConsentResponse();
        response.setStatus(Constants.STATUS_GRANTED);
        response.setDealershipIdentifier(request.getDealershipIdentifier());
        response.setCustomerIdentifier(request.getCustomerIdentifier());
        return response;
    }
}
