package org.mokpouniv.computerDept_backend.forum;

import lombok.RequiredArgsConstructor;
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
    public String getItemNameForCreate(@RequestBody ForumDTO forumDTO
                    ) {
        forumService.addItem(forumDTO.toForumEntity());
        return "redirect:/forum/list";
    }
    @GetMapping("/search")
    public String getItemNameForSearch(@RequestParam("title") String title){

        return forumService.readItem(title);
    }
    //삭제 기능에 삭제 메세지 떴으면 좋겠음 -> dto형태로
    //true false, 에러 메세지 보여주기
    @GetMapping("/delete")
    public boolean getItemNameForDelete(@RequestParam("id") String id){

        return forumService.deleteItem(id);
    }
    @PostMapping("/update")
    public String getItemNameForUpdate(@RequestBody ForumDTO forumDTO
    ) {
        forumService.updateItem(forumDTO.toForumEntity());
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
