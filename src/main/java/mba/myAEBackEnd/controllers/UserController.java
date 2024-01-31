package mba.myAEBackEnd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.mapper.UserMapper;
import mba.myAEBackEnd.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {


    private UserMapper userMapper;
    private UserRepository userRepository;


    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "hello world";
    }
}
