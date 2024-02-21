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
    @Mapping(target = "activity",source = "activity.label")
    UserDto toUserDto(User user);

    @Mapping(target = "userId" , ignore = true)
    User signUpToUser(SignUpDto signUpDto);



}
