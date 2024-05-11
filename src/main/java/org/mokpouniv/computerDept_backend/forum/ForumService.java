package org.mokpouniv.computerDept_backend.forum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ForumService {

    final ForumRepository forumRepo;

    // Create
    public boolean addItem(ForumEntity forumEntity) {
        try {
            // UUID를 사용하여 고유한 아이디 생성
            forumEntity.setId(UUID.randomUUID().toString());
            forumRepo.save(forumEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Read
    public String readItem(String title) {
        ForumEntity item = forumRepo.findAllByTitleRegex(title);
        return item != null ?
                "Title: " + item.getTitle() + " Author: " + item.getAuthor() + " Content: " + item.getContent()
                : "Item not found.";
    }

    // Delete (Using ID)
    public boolean deleteItem(String id) {
        try {
            Optional<ForumEntity> optionalItem = forumRepo.findById(id);
            if (optionalItem.isPresent()) {
                forumRepo.delete(optionalItem.get());
                return true;
            } else {
                return false; // 해당 id에 해당하는 게시물이 없을 경우 false 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 예외 발생 시 false 반환
        }
    }

    // Update (Using ID)
    public boolean updateItem(ForumEntity forumEntity) {
        try {
            Optional<ForumEntity> optionalItem = forumRepo.findById(forumEntity.getId());
            if (optionalItem.isPresent()) {
                ForumEntity item = optionalItem.get();

                // 새로운 정보로 업데이트
                item.setTitle(forumEntity.getTitle());
                item.setAuthor(forumEntity.getAuthor());
                item.setContent(forumEntity.getContent());
                forumRepo.save(item);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all item names
    public List<String> getItemNames() {
        return forumRepo.findAll().stream().map(ForumEntity::getTitle).collect(Collectors.toList());
    }
}