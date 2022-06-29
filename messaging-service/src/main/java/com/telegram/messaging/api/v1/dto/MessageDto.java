package com.telegram.messaging.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MessageDto {

    private UUID id;
    private UUID senderAccountId;
    private UUID toDialogueId;
    private UUID parentMessageId;
    private String text;

}
