package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.todoList.TodoListDto;
import mba.myAEBackEnd.service.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ToDoListController {

    private TodoListService todoListService;


    @PostMapping("/todoList")
    public ResponseEntity<TodoListDto> createNewTodoList(@RequestBody TodoListDto todoListDto, @AuthenticationPrincipal UserDto userDto){
        TodoListDto dto = todoListService.createNewTodoList(todoListDto,userDto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/todoList")
    public ResponseEntity<TodoListDto> update(@RequestBody TodoListDto todoListDto, @AuthenticationPrincipal UserDto userDto){
        TodoListDto dto = todoListService.updateTodoList(todoListDto,userDto);
        return ResponseEntity.ok(dto);
    }

}
