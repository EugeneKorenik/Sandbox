package com.telegram.messaging.controller;

import com.telegram.messaging.config.SecurityConfiguration;
import com.telegram.messaging.configuration.WebSecurityTestConfiguration;
import com.telegram.messaging.configuration.WebTestConfiguration;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        SecurityConfiguration.class,
        WebTestConfiguration.class,
        WebSecurityTestConfiguration.class
})
public class MessageControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    SecurityConfiguration securityConfiguration;

    MockMvc mockMvc;

    @Before
    public void beforeTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @SneakyThrows
    @WithMockUser
    public void testSendMessageAuthorized() {
        String requestBody = resourceAsString("sendMessageRequest.json");
        String expectedResponseBody = resourceAsString("messageResponse.json");

        mockMvc.perform(
                        post("/api/v1/messages")
                                .content(requestBody)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(
                        content().json(expectedResponseBody)
                );
    }

    @Test
    @SneakyThrows
    public void testSendMessageUnauthorized() {
        String requestBody = resourceAsString("sendMessageRequest.json");

        mockMvc.perform(
                        post("/api/v1/messages")
                                .content(requestBody)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        status().isUnauthorized()
                );
    }

    private String resourceAsString(String fileName) throws Exception {
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
