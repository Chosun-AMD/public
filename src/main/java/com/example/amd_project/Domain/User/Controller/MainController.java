package com.example.amd_project.Domain.User.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 해당 Controller는 Main Controller로 Dashboard 매핑에 사용되는 Controller입니다.
 * @author : 황시준
 * @since : 1.0
 */
@Controller
public class MainController {
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard/index";
    }
}
