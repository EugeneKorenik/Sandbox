package com.telegram.messaging.configuration;

import com.telegram.messaging.api.v1.controller.MessageController;
import com.telegram.messaging.api.v1.mapper.MessageMapperImpl;
import com.telegram.messaging.entity.Account;
import com.telegram.messaging.entity.Dialogue;
import com.telegram.messaging.entity.Message;
import com.telegram.messaging.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@EnableWebMvc
@ContextConfiguration
public class WebTestConfiguration implements WebMvcConfigurer {

    @Bean
    public MessageController messageController() {
        var messageService = mockMessageService();
        var messageMapper = new MessageMapperImpl();

        return new MessageController(messageService, messageMapper);
    }

    private MessageService mockMessageService() {
        var messageService = mock(MessageService.class);
        var stubMessage = stubMessage();

        when(
                messageService.sendMessage(
                        any(String.class),
                        any(UUID.class),
                        any(UUID.class),
                        any(UUID.class)
                )
        ).thenReturn(stubMessage);

        return messageService;
    }

    private Message stubMessage() {
        String messageText = "Hello, how are you?. \n Long time no see";

        Message message = new Message();
        message.setId(UUID.fromString("f11c8386-dad0-4eb8-a606-5e52637351ce"));
        message.setText(messageText);
        message.setDialogue(stubDialogue());
        message.setSender(stubAccount());
        message.setParentMessage(stubParentMessage());

        return message;
    }

    private Account stubAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("aa7d1129-1f0e-4724-b4e3-c12a0268a59c"));
        return account;
    }

    private Dialogue stubDialogue() {
        Dialogue dialogue = new Dialogue();
        dialogue.setId(UUID.fromString("9f7d8591-46f4-4164-8790-977e7d1dea00"));
        return dialogue;
    }

    private Message stubParentMessage() {
        Message message = new Message();
        message.setId(UUID.fromString("a9566fd9-9159-4d19-8181-54f6b62e9a1e"));
        return message;
    }

}
