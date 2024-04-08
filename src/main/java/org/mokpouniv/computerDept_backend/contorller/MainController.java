package org.mokpouniv.computerDept_backend.contorller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class MainController {

    @GetMapping("/oop")
    @ResponseBody
    public String index() {
        return "index";
    }
}
