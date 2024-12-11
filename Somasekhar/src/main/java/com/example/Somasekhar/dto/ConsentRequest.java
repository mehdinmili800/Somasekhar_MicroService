package com.example.Somasekhar.dto;


import com.example.Somasekhar.util.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ConsentRequest {

    @NotEmpty(message = Constants.ERROR_DEALERSHIP_EMPTY)
    private String dealershipIdentifier;

    @NotEmpty(message = Constants.ERROR_CUSTOMER_EMPTY)
    private String customerIdentifier;

    @NotEmpty(message = "consentMessage is empty")
    private String consentMessage;
}
