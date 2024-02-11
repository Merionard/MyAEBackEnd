package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CRA.MonthYearDto;
import mba.myAEBackEnd.dto.CRA.WorkDayDto;
import mba.myAEBackEnd.dto.CRA.WorkPeriodDto;
import mba.myAEBackEnd.dto.CRA.WorkPeriodLineDto;
import mba.myAEBackEnd.entity.*;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.mapper.WorkPeriodMapper;
import mba.myAEBackEnd.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CraService {

    private WorkPeriodMapper workPeriodMapper;
    private WorkPeriodRepository workPeriodRepository;
    private UserService userService;
    private CustomerRepository customerRepository;
    private WorkPeriodLineRepository workPeriodLineRepository;
    private WorkDayRepository workDayRepository;


    public WorkPeriodDto fetchWorkPeriodDtoByDateAndEmail(MonthYearDto monthYearDto, String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        Optional<WorkPeriod> workPeriod = workPeriodRepository.findByMonthEqualsAndYearEqualsAndUser(monthYearDto.getMonth(), monthYearDto.getYear(), user);
        if (workPeriod.isPresent()) {
            return workPeriodMapper.toDto(workPeriod.get());
        }
        WorkPeriod newWorkPeriod = new WorkPeriod().setUser(user)
                .setMonth(monthYearDto.getMonth())
                .setYear(monthYearDto.getYear());

        workPeriodRepository.save(newWorkPeriod);

        return workPeriodMapper.toDto(newWorkPeriod);

    }

    public WorkPeriodDto addLineOnWorkPeriod(Long workPeriodId) throws BusinessException {

        WorkPeriod workPeriod = workPeriodRepository.findById(workPeriodId).orElseThrow(() -> new BusinessException("No workPeriod for id " + workPeriodId));

        Customer customer = customerRepository.findFirstByUserOrderById(workPeriod.getUser())
                .orElseThrow(() -> new BusinessException("No customer available for this user"));
        workPeriod.addLine(customer.getId());
        workPeriodRepository.save(workPeriod);
        return workPeriodMapper.toDto(workPeriod);
    }

    public WorkPeriodLineDto updateWorkPeriodLine(WorkPeriodLineDto dto) throws BusinessException {
        WorkPeriodLine workPeriodLine = workPeriodLineRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("No WorkPeriodLine with id:" + dto.getId()));

        WorkPeriodLine updated = workPeriodMapper.toWorkPeriodLine(dto);
        updated.setWorkPeriod(workPeriodLine.getWorkPeriod());
        workDayRepository.deleteAllById(dto.getWorkDaysToDelete());
        workPeriodLineRepository.save(updated);
        return workPeriodMapper.toWorkPeriodLineDto(updated);

    }

    public void deleteWorkPeriodLine(Long lineId){
        workPeriodLineRepository.deleteById(lineId);
    }

    public WorkPeriodLineDto addWorkDayOnLine(Long lineId, WorkDayDto workDayDto) throws BusinessException {
        WorkPeriodLine workPeriodLine = workPeriodLineRepository.findById(lineId)
                .orElseThrow(() -> new BusinessException("No WorkPeriodLine with id:" + lineId));

        WorkDay workDay = workPeriodMapper.toWorkDay(workDayDto);
        workPeriodLine.addWorkDay(workDay);
        workPeriodLineRepository.save(workPeriodLine);
        return workPeriodMapper.toWorkPeriodLineDto(workPeriodLine);

    }

    public void deleteWorkDay(Long workDayId){
        workDayRepository.deleteById(workDayId);
    }

    public void updateWorkDay(WorkDayDto workDayDto) throws BusinessException {
        WorkDay workDay = workDayRepository.findById(workDayDto.getId())
                .orElseThrow(() -> new BusinessException("no workDay found with id: " + workDayDto.getId()));

        workDay.setDuration(workDayDto.getDuration());
        workDayRepository.save(workDay);
    }

}



