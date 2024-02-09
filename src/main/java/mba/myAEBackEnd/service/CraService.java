package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CRA.WorkPeriodDto;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.entity.WorkPeriod;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.mapper.WorkPeriodMapper;
import mba.myAEBackEnd.repository.UserRepository;
import mba.myAEBackEnd.repository.WorkPeriodRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CraService {

    private WorkPeriodMapper workPeriodMapper;
    private WorkPeriodRepository workPeriodRepository;
    private UserRepository userRepository;
    private UserService userService;

    public WorkPeriodDto createNewWorkPeriod(WorkPeriodDto dto,String userEmail) throws BusinessException {
        User user = userService.findUserByEmail(userEmail);
        Optional<WorkPeriod> workPeriod = workPeriodRepository.findByMonthEqualsAndYearEqualsAndUser(dto.getMonth(), dto.getYear(), user);
        if(workPeriod.isPresent()){
            throw new BusinessException("Work Period already exist for User " + userEmail +" on month: " + dto.getMonth() + " and year: " + dto.getYear());
        }
        WorkPeriod newWorkPeriod = workPeriodMapper.toWorkPeriod(dto);
        workPeriodRepository.save(newWorkPeriod);
        return workPeriodMapper.toDto(newWorkPeriod);

    }
}
