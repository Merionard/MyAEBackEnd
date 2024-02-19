package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.todoList.TaskDto;
import mba.myAEBackEnd.dto.todoList.TodoListDto;
import mba.myAEBackEnd.entity.Task;
import mba.myAEBackEnd.entity.TodoList;
import mba.myAEBackEnd.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ToDoListMapper {

    @Mapping(target = "user", source = "user")
    TodoList toEntity(TodoListDto dto, User user);

    @Mapping(target = "priorityOrder",source = "order")
    Task toTask(TaskDto taskDto);

    @AfterMapping
    default TodoList complete(@MappingTarget TodoList todoList) {
        todoList.getTasks().forEach(task -> task.setTodoList(todoList));
        return todoList;
    }

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "taskToDelete", ignore = true)
    TodoListDto toDto(TodoList todoList);

    @Mapping(target = "order",source = "priorityOrder")
    TaskDto toTaskDto(Task task);
}
