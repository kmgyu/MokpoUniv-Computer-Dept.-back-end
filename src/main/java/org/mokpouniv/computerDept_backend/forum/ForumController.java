package org.mokpouniv.computerDept_backend.forum;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/forum")
@RestController
public class ForumController {

    private final ForumService forumService;

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
    public String create(@RequestBody ForumDTO forumDTO
                    ) {
        forumService.addItem(forumDTO.toForumEntity());
        return "redirect:/forum/list";
    }

    @GetMapping("/get-names")
    public List<String> getNames() {
        return forumService.getItemNames();
    }
//    @GetMapping("/json")
//    public ForumDTO jsonTest() {
//        return new ForumDTO("title", "content", "writer");
//    }

}
