package com.telegram.messaging.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {

    @Column(name = "text", columnDefinition = "text not null")
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_message_id", columnDefinition = "uuid not null", updatable = false)
    private Message parentMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialogue_id", columnDefinition = "uuid not null", updatable = false)
    private Dialogue dialogue;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", columnDefinition = "uuid not null references account(id)", updatable = false)
    private Account sender;

}
