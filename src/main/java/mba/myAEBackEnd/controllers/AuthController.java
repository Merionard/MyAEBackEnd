package mba.myAEBackEnd.controllers;

import mba.myAEBackEnd.dto.LogInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogInDto logInDto){
        return ResponseEntity.ok().body("suceess");
    }

    @GetMapping("/test")
    public ResponseEntity<String>test(){
        return ResponseEntity.ok("GG");
    }
}
