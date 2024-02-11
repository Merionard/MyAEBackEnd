package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CRA.MonthYearDto;
import mba.myAEBackEnd.dto.CRA.WorkDayDto;
import mba.myAEBackEnd.dto.CRA.WorkPeriodDto;
import mba.myAEBackEnd.dto.CRA.WorkPeriodLineDto;
import mba.myAEBackEnd.dto.MessageJsonDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.WorkPeriodLine;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.service.CraService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CraController {

    private CraService craService;

    @PostMapping("/cra")
    public ResponseEntity<WorkPeriodDto> fetchWorkPeriod(@RequestBody MonthYearDto date, @AuthenticationPrincipal UserDto userDto) {

        WorkPeriodDto workPeriod = craService.fetchWorkPeriodDtoByDateAndEmail(date, userDto.getEmail());
        return ResponseEntity.ok(workPeriod);
    }

    @GetMapping("/cra/{id}")
    public ResponseEntity<WorkPeriodDto> allLineOnWorkPeriod(@AuthenticationPrincipal UserDto userDto, @PathVariable Long id) throws BusinessException {
        WorkPeriodDto workPeriodDto = craService.addLineOnWorkPeriod(id);
        return ResponseEntity.ok(workPeriodDto);
    }

    @PostMapping("/cra/workPeriodLine")
    public ResponseEntity<WorkPeriodLineDto> updateWorkPeriodLine(@RequestBody WorkPeriodLineDto workPeriodLineDto) throws BusinessException {

        WorkPeriodLineDto dto = craService.updateWorkPeriodLine(workPeriodLineDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/cra/workPeriodLine/{id}")
    public ResponseEntity<MessageJsonDto> deleteWorkPeriodLine(@PathVariable Long id){
        craService.deleteWorkPeriodLine(id);
        return ResponseEntity.ok(new MessageJsonDto("Ligne supprimée avec succès"));
    }

    @PutMapping("/cra/workDay/{id}")
    public ResponseEntity<WorkPeriodLineDto> addWorkDay(@PathVariable Long id, @RequestBody WorkDayDto workDayDto) throws BusinessException {
        WorkPeriodLineDto dto = craService.addWorkDayOnLine(id,workDayDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/cra/workDay/{id}")
    public ResponseEntity<MessageJsonDto> deleteWorkDay(@PathVariable Long id){
        craService.deleteWorkDay(id);
        return ResponseEntity.ok(new MessageJsonDto("Jour de travail supprimé avec succès"));
    }

    @PostMapping("/cra/workDay")
    public ResponseEntity<MessageJsonDto> updateWorkDay( @RequestBody WorkDayDto workDayDto) throws BusinessException {
        craService.updateWorkDay(workDayDto);
        return ResponseEntity.ok(new MessageJsonDto("Maj effectuée avec succès"));
    }

}
