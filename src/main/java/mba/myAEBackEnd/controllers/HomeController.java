package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

    private DashboardService dashboardService;

    @GetMapping("/dashboard/currentCA")
    public ResponseEntity<Double> getCurrentCa(@AuthenticationPrincipal UserDto userDto){
        return ResponseEntity.ok(dashboardService.getCurrentYearCA(userDto));
    }
}
