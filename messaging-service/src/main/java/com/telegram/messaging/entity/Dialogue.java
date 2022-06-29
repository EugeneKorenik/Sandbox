package com.telegram.messaging.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "dialogue")
@AllArgsConstructor
@NoArgsConstructor
public class Dialogue extends BaseEntity {

    @Column(name = "name", columnDefinition = "varchar(256) not null")
    private String name;

    @OneToMany(mappedBy = "dialogue", fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToOne
    @JoinColumn(name = "created_by", columnDefinition = "uuid not null references account(id)", updatable = false)
    private Account createdBy;

}
