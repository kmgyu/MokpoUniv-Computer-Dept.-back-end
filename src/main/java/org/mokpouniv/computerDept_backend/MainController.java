package org.mokpouniv.computerDept_backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/oop")
    @ResponseBody
    public String index() {
        return "hi there?";
    }
}
