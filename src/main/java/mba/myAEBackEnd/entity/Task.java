package mba.myAEBackEnd.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.enums.TaskStatus;

import java.time.ZonedDateTime;

@Enabled
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private Integer priorityOrder;
    private ZonedDateTime createdAt;
    private boolean critical = false;
    @ManyToOne
    private TodoList todoList;

}
