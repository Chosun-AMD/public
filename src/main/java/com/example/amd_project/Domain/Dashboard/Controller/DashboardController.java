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
    @GetMapping("/admin")
    public String admin(){
        return "dashboard/index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        // List<ResponseDataDTO> responseDataDTO = dashboardService.getData();
        // return responseDataDTO;
        //log.info("responseDataDTO : {}", responseDataDTO);
        //log.info("responseDataDTO.get(0).getId() : {}", responseDataDTO.get(0).getId());
        // return "dashboard/index";
        return "redirect:http://117.16.17.165:3000";
    }

    @GetMapping("/monitoring")
    public String monitoring(){
        return "redirect:http://117.16.17.165:30004/d/kube-cluster/kubernetes-cluster-prometheus?orgId=1&refresh=10s";
    }

    @GetMapping("/jenkins")
    public String jenkins(){
        return "redirect:http://117.16.17.165:30000";
    }

    @GetMapping("/argocd")
    public String argocd(){
        return "redirect:http://117.16.17.165:32596";
    }
}
