package mba.myAEBackEnd.mapper;

import lombok.Data;
import mba.myAEBackEnd.dto.CRA.WorkPeriodDto;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.entity.WorkPeriod;
import mba.myAEBackEnd.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Data
public abstract class WorkPeriodMapper {

    @Autowired
    private  UserRepository userRepository;

   @Mapping(target = "user",source = "userId",qualifiedByName = "mapUser")
    public abstract WorkPeriod toWorkPeriod(WorkPeriodDto dto);

   @Mapping(target = "userId", source = "user.userId")
   public abstract WorkPeriodDto toDto(WorkPeriod workPeriod);

   @Named("mapUser")
   public User mapUser(Long userId) throws Exception {
       return userRepository.findById(userId).orElseThrow(()->new Exception("User not found"));
   }


}
