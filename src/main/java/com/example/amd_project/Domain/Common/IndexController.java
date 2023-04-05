package com.example.amd_project.Domain.Common;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @GetMapping("common/index")
    public String main(Model model, HttpServletResponse response) throws JsonProcessingException {
        return "main/index";
    }
}
