package com.example.Somasekhar.controller;


import com.example.Somasekhar.dto.ConsentRequest;
import com.example.Somasekhar.dto.ConsentResponse;
import com.example.Somasekhar.service.ConsentService;
import com.example.Somasekhar.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consent")
public class ConsentController {

    @Autowired
    private ConsentService consentService;

    @Operation(summary = "Get consent status", description = "Retrieves the consent status for a given dealership and customer identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of consent status"),
            @ApiResponse(responseCode = "400", description = "Invalid input - missing dealershipIdentifier or customerIdentifier")
    })
    @PostMapping("/retrieve")
    public ResponseEntity<?> getConsentStatus(
            @Parameter(description = "Identifier for the dealership", required = true)
            @RequestParam String dealershipIdentifier,

            @Parameter(description = "Identifier for the customer", required = true)
            @RequestParam String customerIdentifier) {

        if (dealershipIdentifier == null || dealershipIdentifier.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Constants.ERROR_DEALERSHIP_EMPTY);
        }

        if (customerIdentifier == null || customerIdentifier.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Constants.ERROR_CUSTOMER_EMPTY);
        }

        ConsentResponse response = consentService.getConsentStatus(dealershipIdentifier, customerIdentifier);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create consent record", description = "Creates a consent record for a given dealership and customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful creation of consent record"),
            @ApiResponse(responseCode = "400", description = "Invalid input - missing required fields")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createConsent(@RequestBody ConsentRequest request) {
        if (request.getDealershipIdentifier() == null || request.getDealershipIdentifier().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Constants.ERROR_DEALERSHIP_EMPTY);
        }

        if (request.getCustomerIdentifier() == null || request.getCustomerIdentifier().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Constants.ERROR_CUSTOMER_EMPTY);
        }

        if (request.getCustomerIdentifier() == null || request.getCustomerIdentifier().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Constants.ERROR_CUSTOMER_EMPTY);
        }

        ConsentResponse response = consentService.createConsent(request);
        return ResponseEntity.ok(response);
    }

}
