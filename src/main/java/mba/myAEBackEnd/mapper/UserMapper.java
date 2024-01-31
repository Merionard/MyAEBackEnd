package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.SignUpDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "userId" , ignore = true)
    @Mapping(source = "password",target = "password" ,qualifiedByName = "convertPassword")
    User signUpToUser(SignUpDto signUpDto);

    @Named("convertPassword")
     static String charsToString(char[] password){
        return new String(password);
    }
}
