package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.todoList.TodoListDto;
import mba.myAEBackEnd.entity.TodoList;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.ToDoListMapper;
import mba.myAEBackEnd.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoListService {

    private UserService userService;
    private ToDoListMapper mapper;
    private TodoListRepository todoListRepository;


    public List<TodoListDto> getAllByUser(UserDto userDto){
        User user = userService.findUserByEmail(userDto.getEmail());
        return todoListRepository.findAllByUser(user).stream()
                .map(t->mapper.toDto(t))
                .toList();
    }

    public TodoListDto createUpdateTodoList(TodoListDto dto, UserDto userDto){

        User user = userService.findUserByEmail(userDto.getEmail());
        TodoList todoList = mapper.toEntity(dto,user);
        todoListRepository.save(todoList);
        return mapper.toDto(todoList);
    }

    public void deleteTodoList(String title){
        todoListRepository.deleteById(title);
    }
}
