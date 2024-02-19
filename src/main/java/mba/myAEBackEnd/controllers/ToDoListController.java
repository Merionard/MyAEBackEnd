package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.MessageJsonDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.todoList.TodoListDto;
import mba.myAEBackEnd.service.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ToDoListController {

    private TodoListService todoListService;


    @PostMapping("/todoList")
    public ResponseEntity<TodoListDto> createNewTodoList(@RequestBody TodoListDto todoListDto, @AuthenticationPrincipal UserDto userDto){
        TodoListDto dto = todoListService.createUpdateTodoList(todoListDto,userDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("todoList/{title}")
    public ResponseEntity<MessageJsonDto> deleteTodoList(@PathVariable String title){
        todoListService.deleteTodoList(title);
        return  ResponseEntity.ok(new MessageJsonDto("Delete with success!"));
    }

    @GetMapping("/todoList")
    public ResponseEntity<List<TodoListDto>> getAll(@AuthenticationPrincipal UserDto userDto){
        List<TodoListDto> todos = todoListService.getAllByUser(userDto);
        return ResponseEntity.ok(todos);
    }

}
