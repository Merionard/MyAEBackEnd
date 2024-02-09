package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CRA.WorkPeriodDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.WorkPeriod;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.service.CraService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class CraController {

    private CraService craService;

    @PostMapping("/cra")
    public ResponseEntity<WorkPeriodDto> newWorkPeriod(@RequestBody WorkPeriodDto dto,@AuthenticationPrincipal UserDto userDto) throws BusinessException {

        WorkPeriodDto newWorkPeriod = craService.createNewWorkPeriod(dto, userDto.getEmail());
        return  ResponseEntity.ok(newWorkPeriod);
    }

}
