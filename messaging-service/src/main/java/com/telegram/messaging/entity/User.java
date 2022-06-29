package com.telegram.messaging.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "username", columnDefinition = "varchar(256) unique not null")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(256) not null")
    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Account account;

}
