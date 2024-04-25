package org.mokpouniv.computerDept_backend.forum;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ForumService {

    ForumRepository forumRepo;

    public boolean addItem(ForumEntity forumEntity) {
        try {
            forumRepo.save(forumEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getItemNames() {
        return forumRepo.findAll().stream().map(ForumEntity::getTitle).collect(Collectors.toList());
    }

}
