package com.example.amd_project.Domain.Dashboard.Controller;

import com.example.amd_project.Domain.Dashboard.DTO.ResponseDataDTO;
import com.example.amd_project.Domain.Dashboard.Service.DashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 해당 Controller는 Main Controller로 Dashboard 매핑에 사용되는 Controller입니다.
 * @author : 황시준
 * @since : 1.0
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class DashboardController {
    private final DashboardServiceImpl dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(){
        List<ResponseDataDTO> responseDataDTO = dashboardService.getData();
        // return responseDataDTO;
        log.info("responseDataDTO : {}", responseDataDTO);
        log.info("responseDataDTO.get(0).getId() : {}", responseDataDTO.get(0).getId());
        return "dashboard/index";
    }
}
