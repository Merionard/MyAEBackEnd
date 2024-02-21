package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mba.myAEBackEnd.dto.MessageJsonDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<MessageJsonDto> updateUser(@AuthenticationPrincipal UserDto userDto, @RequestBody UserDto updatedUserDto){
        userService.updateUser(userService.findUserByEmail(userDto.getEmail()),updatedUserDto);
        return ResponseEntity.ok(new MessageJsonDto("success"));
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal UserDto userDto){
        return ResponseEntity.ok(userDto);
    }
}
