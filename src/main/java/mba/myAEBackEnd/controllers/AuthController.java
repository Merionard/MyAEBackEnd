package mba.myAEBackEnd.controllers;

import lombok.RequiredArgsConstructor;
import mba.myAEBackEnd.config.UserAuthProvider;
import mba.myAEBackEnd.dto.CredentialsDto;
import mba.myAEBackEnd.dto.SignUpDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthProvider.createToken(userDto.getLogin()));

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto>register(@RequestBody SignUpDto signUpDto){

        UserDto user = userService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
