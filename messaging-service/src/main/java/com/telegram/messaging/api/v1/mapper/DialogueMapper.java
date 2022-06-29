package com.telegram.messaging.api.v1.mapper;

import com.telegram.messaging.api.v1.dto.DialogueDto;
import com.telegram.messaging.entity.Dialogue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DialogueMapper {

    Dialogue toEntity(DialogueDto dialogueDto);

    DialogueDto toDto(Dialogue dialogue);

    List<DialogueDto> toDto(List<Dialogue> dialogueList);

}
