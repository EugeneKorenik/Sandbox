package com.telegram.messaging.api.v1.controller;

import com.telegram.messaging.api.v1.dto.MessageDto;
import com.telegram.messaging.api.v1.mapper.MessageMapper;
import com.telegram.messaging.entity.Message;
import com.telegram.messaging.service.MessageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    public MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDto) {
        Message message = messageService.sendMessage(
                messageDto.getText(),
                messageDto.getSenderAccountId(),
                messageDto.getToDialogueId(),
                messageDto.getParentMessageId()
        );

        MessageDto response = messageMapper.asDto(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(response);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("messageId") UUID messageId) {
        messageService.deleteForAll(messageId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMessageForAccount(@RequestParam("messageId") UUID messageId,
                                                        @RequestParam("accountId") UUID accountId) {
        messageService.deleteForWatcher(messageId, accountId);
        return ResponseEntity.noContent().build();
    }

}
