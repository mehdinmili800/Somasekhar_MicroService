package com.example.Somasekhar.controller;

import com.example.Somasekhar.dto.ConsentResponse;
import com.example.Somasekhar.service.ConsentService;
import com.example.Somasekhar.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class ConsentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ConsentService consentService; // Correctly mock the service, not the controller

    @InjectMocks
    private ConsentController consentController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consentController).build();
    }

    @Test
    public void whenValidRequest_thenReturns200() throws Exception {
        // Arrange
        String dealershipIdentifier = "12345";
        String customerIdentifier = "67890";
        ConsentResponse response = new ConsentResponse();
        response.setStatus(Constants.STATUS_GRANTED);
        response.setDealershipIdentifier(dealershipIdentifier);
        response.setCustomerIdentifier(customerIdentifier);

        given(consentService.getConsentStatus(dealershipIdentifier, customerIdentifier)).willReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/v1/consent/retrieve")
                        .param("dealershipIdentifier", dealershipIdentifier)
                        .param("customerIdentifier", customerIdentifier)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Constants.STATUS_GRANTED))
                .andExpect(jsonPath("$.dealershipIdentifier").value(dealershipIdentifier))
                .andExpect(jsonPath("$.customerIdentifier").value(customerIdentifier));
    }

    @Test
    public void whenDealershipIdentifierIsEmpty_thenReturns400() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/v1/consent/retrieve")
                        .param("dealershipIdentifier", "")
                        .param("customerIdentifier", "67890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value(Constants.ERROR_DEALERSHIP_EMPTY));
    }

    @Test
    public void whenCustomerIdentifierIsEmpty_thenReturns400() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/v1/consent/retrieve")
                        .param("dealershipIdentifier", "12345")
                        .param("customerIdentifier", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value(Constants.ERROR_CUSTOMER_EMPTY));
    }
}
