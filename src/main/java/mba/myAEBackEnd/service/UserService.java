package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.authentication.CredentialsDto;
import mba.myAEBackEnd.dto.authentication.SignUpDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.Role;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.enums.ActiviteEnum;
import mba.myAEBackEnd.enums.RoleEnum;
import mba.myAEBackEnd.mapper.UserMapper;
import mba.myAEBackEnd.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserDto findDtoByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User with " + email + " not found"));

        return userMapper.toUserDto(user);

    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User with " + email + " not found"));
    }

    public UserDto login(CredentialsDto credentialsDto){

        User user = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("User with " + credentialsDto.getEmail() + " not found"));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),user.getPassword())){
            return userMapper.toUserDto(user);
        }

        throw new BadCredentialsException("Invalid password");

    }

    public UserDto register(SignUpDto signUpDto){

        Optional<User> optionalUser =  userRepository.findByEmail(signUpDto.getEmail());
        if(optionalUser.isPresent()){
            throw new BadCredentialsException("Login already use!");
        }

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        Role role = new Role();
        role.setValue(RoleEnum.USER);
        user.setRole(role);
        User savedUser = userRepository.save(user);

        return  userMapper.toUserDto(savedUser);


    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User with " + email + " not found"));
    }

    public void updateUser(User user,UserDto updateDto){
        user.setActivity(ActiviteEnum.findByLabel(updateDto.getActivity()));
        userRepository.save(user);
    }
}
