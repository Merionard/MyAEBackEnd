package mba.myAEBackEnd.dto.todoList;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TodoListDto {

    private String title;
    private Long userId;
    private Set<TaskDto> tasks = new HashSet<>();
    private Set<Long> taskToDelete = new HashSet<>();
}
