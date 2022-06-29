package com.telegram.messaging.api.v1.dto;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto
{
  private UUID id;
  private String firstName;
  private String secondName;
}
