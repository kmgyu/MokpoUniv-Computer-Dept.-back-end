package org.mokpouniv.computerDept_backend.forum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ForumService {

    final ForumRepository forumRepo;

    // Create
    public boolean addItem(ForumDTO forumDTO) {
        forumDTO.setId(UUID.randomUUID().toString());
        ForumEntity forumEntity = forumDTO.toForumEntity();
        if (forumEntity.getTitle() == null || forumEntity.getTitle().isBlank()) { return false; }
        else if (forumEntity.getAuthor() == null || forumEntity.getAuthor().isBlank()) { return false; }
        else if (forumEntity.getContent() == null || forumEntity.getContent().isBlank()) { return false; }
        else { forumRepo.save(forumEntity); return true; }
    }

    // Read
    public List<ForumEntity> readItemsByTitle(String title) {
        return forumRepo.findAllByTitle(title);
    }

    // Delete (Using ID)
    public boolean deleteItem(String id) {
        if (forumRepo.existsById(id)) {
            forumRepo.deleteById(id);
            return true;
        } else { return false; }
    }

    // Update (Using ID)
    public boolean updateItem(String id) {
        Optional<ForumEntity> optionalEntity = forumRepo.findById(id);
        if (optionalEntity.isPresent()) {
            ForumEntity item = optionalEntity.get();
            item.setTitle(item.getTitle());
            item.setContent(item.getContent());
            forumRepo.save(item);
            return true;
        } else { return false; }
    }

    // Get all item names
    public List<String> getItemNames() {
        return forumRepo.findAll().stream().map(ForumEntity::getTitle).collect(Collectors.toList());
    }
}