package com.example.amd_project.Domain.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {

    @RequestMapping("file/hello")
    public String hello(Model model){
        model.addAttribute("message", "안녕하세요");

        return "test";
    }
}
