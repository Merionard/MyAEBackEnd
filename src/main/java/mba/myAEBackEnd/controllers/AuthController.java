package mba.myAEBackEnd.controllers;

import lombok.RequiredArgsConstructor;
import mba.myAEBackEnd.service.JwtService;
import mba.myAEBackEnd.dto.CredentialsDto;
import mba.myAEBackEnd.dto.SignUpDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Validated CredentialsDto credentialsDto){
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthProvider.createToken(userDto.getEmail()));

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto>register(@RequestBody SignUpDto signUpDto){

        UserDto user = userService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getEmail())).body(user);
    }
}
