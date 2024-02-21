package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.todoList.TaskDto;
import mba.myAEBackEnd.dto.todoList.TodoListDto;
import mba.myAEBackEnd.entity.Task;
import mba.myAEBackEnd.entity.TodoList;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.ToDoListMapper;
import mba.myAEBackEnd.repository.TaskRepository;
import mba.myAEBackEnd.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoListService {

    private UserService userService;
    private ToDoListMapper mapper;
    private TodoListRepository todoListRepository;
    private TaskRepository taskRepository;


    public List<TodoListDto> getAllByUser(UserDto userDto) {
        User user = userService.findUserByEmail(userDto.getEmail());
        return todoListRepository.findAllByUser(user).stream()
                .map(t -> mapper.toDto(t))
                .toList();
    }

    public TodoListDto createUpdateTodoList(TodoListDto dto, UserDto userDto) {

        User user = userService.findUserByEmail(userDto.getEmail());
        TodoList todoList = mapper.toEntity(dto, user);
        todoListRepository.save(todoList);
        return mapper.toDto(todoList);
    }

    public void deleteTodoList(String title) {
        todoListRepository.deleteById(title);
    }

    public TaskDto updateTask(TaskDto taskDto) {
        Task taskToUpdate = taskRepository.findById(taskDto.getId()).orElseThrow();
        Task task = mapper.toTask(taskDto);
        task.setTodoList(taskToUpdate.getTodoList());
        taskRepository.save(task);
        return taskDto;

    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
