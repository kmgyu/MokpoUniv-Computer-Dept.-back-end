package org.mokpouniv.computerDept_backend.forum;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/forum")
@RestController
public class ForumController {
    @GetMapping("/index")
    public String index() {
        return "hi there? this is index of Forum.";
    }

//    DB에서 조회해서 데이터를 가져와야 됨.
    @GetMapping("/list")
    public String list() {
        return "hi";
    }

    @PostMapping("/create")
    public String post(@RequestBody ForumDTO forumDTO
//                        @RequestParam(value = "title") String title,
//                       @RequestParam(value = "content") String content,
//                       @RequestParam(value = "writer") String writer
                    ) {
        System.out.println(forumDTO.getTitle() + " is successfully uploaded");
        return "redirect:/forum/list";
    }

    @GetMapping("/json")
    public ForumDTO jsonTest() {
        return new ForumDTO("title", "content", "writer");
    }
}
