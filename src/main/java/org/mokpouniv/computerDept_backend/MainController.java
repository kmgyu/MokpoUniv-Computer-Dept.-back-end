package org.mokpouniv.computerDept_backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "mainpage.html";
    }

    @GetMapping("/curriculum1")
    public String curr1() { return "curriculum1.html"; }

    @GetMapping("/curriculum2")
    public String curr2() { return "curriculum2.html"; }

    @GetMapping("/curriculum3")
    public String curr3() { return "curriculum3.html"; }

    @GetMapping("/curriculum4")
    public String curr4() { return "curriculum4.html"; }

    @GetMapping("/sitemap")
    public String sitemap() { return "sitemap.html"; }

    @GetMapping("/signup")
    public String signup() { return "signup.html"; }

    @GetMapping("/login")
    public String login() { return "login.html"; }
}
