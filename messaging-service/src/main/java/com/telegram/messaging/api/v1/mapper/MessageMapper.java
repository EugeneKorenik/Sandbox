package com.telegram.messaging.api.v1.mapper;

import com.telegram.messaging.api.v1.dto.MessageDto;
import com.telegram.messaging.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "parentMessage.id", target = "parentMessageId")
    @Mapping(source = "dialogue.id", target = "toDialogueId")
    @Mapping(source = "sender.id", target = "senderAccountId")
    MessageDto asDto(Message message);

}
