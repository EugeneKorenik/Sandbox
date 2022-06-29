package com.telegram.messaging.api.v1.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialogueDto
{
  private String name;
  private List<UUID> participantIdList;
}
