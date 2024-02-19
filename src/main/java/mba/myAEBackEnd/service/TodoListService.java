package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.todoList.TodoListDto;
import mba.myAEBackEnd.entity.TodoList;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.ToDoListMapper;
import mba.myAEBackEnd.repository.TodoListRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoListService {

    private UserService userService;
    private ToDoListMapper mapper;
    private TodoListRepository todoListRepository;
    public TodoListDto createNewTodoList(TodoListDto dto, UserDto userDto){

        User user = userService.findUserByEmail(userDto.getEmail());
        TodoList todoList = mapper.toEntity(dto,user);
        todoListRepository.save(todoList);
        return mapper.toDto(todoList);
    }

    public TodoListDto updateTodoList(TodoListDto dto,UserDto userDto){
        User user = userService.findUserByEmail(userDto.getEmail());
        TodoList todoList = mapper.toEntity(dto,user);
        todoListRepository.save(todoList);
        return mapper.toDto(todoList);
    }

    public void deleteTodoList(TodoListDto dto,UserDto userDto){
        User user = userService.findUserByEmail(userDto.getEmail());
        TodoList todoList = mapper.toEntity(dto,user);
        todoListRepository.delete(todoList);
    }
}
