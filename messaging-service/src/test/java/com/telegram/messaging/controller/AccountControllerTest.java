package com.telegram.messaging.controller;

import com.telegram.messaging.api.v1.controller.AccountController;
import com.telegram.messaging.api.v1.mapper.AccountMapperImpl;
import com.telegram.messaging.entity.Account;
import com.telegram.messaging.service.AccountService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class AccountControllerTest {

    MockMvc mockMvc;

    @Before
    public void initBeforeTest() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(accountController())
                .build();
    }

    private AccountController accountController() {
        Account testAccount = createTestAccount();
        AccountService service = mock(AccountService.class);
        when(service.create(any(Account.class))).thenReturn(testAccount);
        when(service.findById(any(UUID.class))).thenReturn(testAccount);
        return new AccountController(service, new AccountMapperImpl());
    }

    private Account createTestAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("6ad9c248-1b2d-4c29-b77a-ae5d0ecbfa4a"));
        account.setFirstName("Eugen");
        account.setSecondName("Korenik");
        return account;
    }

    @Test
    public void testCreateAccountSuccessful() throws Exception {
        String requestBody = resourceAsString("createAccountRequest.json");
        String expectedResponseBody = resourceAsString("accountResponse.json");

        mockMvc.perform(
                        post("/api/v1/accounts")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        content().json(expectedResponseBody)
                );
    }

    @Test
    public void testFindAccountById() throws Exception {
        String expectedResponseBody = resourceAsString("accountResponse.json");

        mockMvc.perform(
                        get("/api/v1/accounts/{id}", UUID.randomUUID())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        content().json(expectedResponseBody)
                );
    }


    public String resourceAsString(String fileName) throws Exception {
        try (
                InputStream inputStream = AccountControllerTest.class.getResourceAsStream(fileName);
        ) {
            assert inputStream != null;
            try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            ) {
                return IOUtils.toString(reader);
            }
        }
    }

}