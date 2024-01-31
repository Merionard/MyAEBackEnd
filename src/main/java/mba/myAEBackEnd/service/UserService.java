package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mba.myAEBackEnd.dto.CredentialsDto;
import mba.myAEBackEnd.dto.SignUpDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.UserMapper;
import mba.myAEBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserDto findByLogin(String login){
        User user = userRepository.findByLogin(login)
                .orElseThrow(()->new UsernameNotFoundException("User with " + login + " not found"));

        return userMapper.toUserDto(user);

    }

    public UserDto login(CredentialsDto credentialsDto){

        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(()->new UsernameNotFoundException("User with " + credentialsDto.getLogin() + " not found"));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),user.getPassword())){
            return userMapper.toUserDto(user);
        }

        throw new BadCredentialsException("Invalid password");

    }

    public UserDto register(SignUpDto signUpDto){

        Optional<User> optionalUser =  userRepository.findByLogin(signUpDto.getLogin());
        if(optionalUser.isPresent()){
            throw new BadCredentialsException("Login already use!");
        }

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        User savedUser = userRepository.save(user);

        return  userMapper.toUserDto(savedUser);


    }
}
