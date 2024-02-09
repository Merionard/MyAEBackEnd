package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.authentication.SignUpDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "role", source = "role.label")
    UserDto toUserDto(User user);

    @Mapping(target = "userId" , ignore = true)
    User signUpToUser(SignUpDto signUpDto);

    @Named("convertPassword")
     static String charsToString(char[] password){
        return new String(password);
    }



}
