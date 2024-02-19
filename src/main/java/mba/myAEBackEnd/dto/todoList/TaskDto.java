package mba.myAEBackEnd.dto.todoList;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private int order = 1;
    private ZonedDateTime createdAt;
    private boolean critical = false;
}
