package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {

    // notice에서 사용하므로 notice의 기능만을 사용하도록 이름 명시를 해야하나?
    final ForumRepository forumRepository;

    /**
     * id를 uuid를 이용해 난수 문자열 생성
     * @param noticeDetailDTO
     * @return
     */
    public boolean addNotice(NoticeDetailDTO noticeDetailDTO) {
        noticeDetailDTO.setId(UUID.randomUUID().toString());
        NoticeEntity noticeEntity = noticeDetailDTO.toNoticeEntity();

        forumRepository.save(noticeEntity);
        return true;
    }

    // Read
    public List<NoticeSummaryDTO> searchNoticeByTitle(String title) {
        List<NoticeEntity> noticeEntities = forumRepository.findNoticeAllByTitle(title);
        List<NoticeSummaryDTO> noticeDTOs = new ArrayList<>();

        for (NoticeEntity entity : noticeEntities) {
            noticeDTOs.add(entity.toNoticeSummaryDTO());
        }

        return noticeDTOs;
    }

    // Delete (Using ID)

    /**
     * false 오류에 대한 정확한 메시지 출력 불가능. refactoring 필요
     * @param id
     * @return
     */
    public boolean deleteNotice(String id) {
        if (forumRepository.existsById(id)) {
            forumRepository.deleteById(id);
            return true;
        } else { return false; }
    }

    // Update (Using ID)
    public boolean updateNotice(String id) {
        Optional<NoticeEntity> optionalEntity = forumRepository.findById(id);
        if (optionalEntity.isPresent()) {
            NoticeEntity item = optionalEntity.get();
            item.setTitle(item.getTitle());
            item.setContent(item.getContent());
            forumRepository.save(item);
            return true;
        } else { return false; }
    }

    // Get all item names
    // 안쓰는 더미 코드
    public List<String> getItemNames() {
        return forumRepository.findAll().stream().map(NoticeEntity::getTitle).collect(Collectors.toList());
    }
}