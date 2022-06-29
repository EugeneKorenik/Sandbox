package com.telegram.messaging.entity;

import com.telegram.messaging.entity.watcher.DialogueWatcher;
import com.telegram.messaging.entity.watcher.MessageWatcher;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "varchar(256) not null")
    private String firstName;

    @Column(name = "second_name", columnDefinition = "varchar(256) not null")
    private String secondName;

    @Column(name = "birthday", columnDefinition = "varchar(256)")
    private LocalDate birthDay;

    @OneToMany(mappedBy = "watcher", fetch = FetchType.LAZY)
    private List<DialogueWatcher> dialogues;

    @OneToMany(mappedBy = "watcher", fetch = FetchType.LAZY)
    private List<MessageWatcher> messages;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
