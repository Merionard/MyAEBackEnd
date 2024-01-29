package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "id" , ignore = true)
    User toUser(UserDto userDto);
}
