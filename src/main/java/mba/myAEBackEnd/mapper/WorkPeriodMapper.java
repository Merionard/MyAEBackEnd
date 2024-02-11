package mba.myAEBackEnd.mapper;

import lombok.Data;
import mba.myAEBackEnd.dto.CRA.WorkDayDto;
import mba.myAEBackEnd.dto.CRA.WorkPeriodDto;
import mba.myAEBackEnd.dto.CRA.WorkPeriodLineDto;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.entity.WorkDay;
import mba.myAEBackEnd.entity.WorkPeriod;
import mba.myAEBackEnd.entity.WorkPeriodLine;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.repository.UserRepository;
import mba.myAEBackEnd.repository.WorkDayRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Data
public abstract class WorkPeriodMapper {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private WorkDayRepository workDayRepository;

   @Mapping(target = "user",source = "userId",qualifiedByName = "mapUser")
    public abstract WorkPeriod toWorkPeriod(WorkPeriodDto dto);

   @Mapping(target = "userId", source = "user.userId")
   public abstract WorkPeriodDto toDto(WorkPeriod workPeriod);

   @Mapping(target = "workPeriod",ignore = true)
   public abstract WorkPeriodLine toWorkPeriodLine(WorkPeriodLineDto workPeriodLineDto);
   @Mapping(target = "workDaysToDelete" ,ignore = true)
   public abstract WorkPeriodLineDto toWorkPeriodLineDto(WorkPeriodLine workPeriodLine);


   @Mapping(target = "workPeriodLine",ignore = true)
   public abstract WorkDay toWorkDay(WorkDayDto dto);

   @Named("mapUser")
   public User mapUser(Long userId) throws Exception {
       return userRepository.findById(userId).orElseThrow(()->new Exception("User not found"));
   }

   public WorkDay mapWorkDay(Long workDayId) throws BusinessException {
       return workDayRepository.findById(workDayId).orElseThrow(()->new BusinessException("WorkDay not found with id:" + workDayId));
   }



}
