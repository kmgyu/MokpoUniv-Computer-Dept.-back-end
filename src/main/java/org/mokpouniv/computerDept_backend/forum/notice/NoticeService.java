package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class NoticeService {

    // notice에서 사용하므로 notice의 기능만을 사용하도록 이름 명시를 해야하나?
    private final NoticeRepository noticeRepository;

    /**
     * id를 uuid를 이용해 난수 문자열 생성
     * @param noticeDetailDTO
     * @return
     */
    public String saveNotice(NoticeDetailDTO noticeDetailDTO) {
        NoticeEntity noticeEntity = noticeDetailDTO.toNoticeEntity();

        noticeRepository.save(noticeEntity);
        return noticeEntity.getId();
    }

    // Read
    public List<NoticeSummaryDTO> searchNoticeByTitle(String title) {
        List<NoticeEntity> noticeEntities = noticeRepository.findAllNoticeEntityByTitle(title);
        List<NoticeSummaryDTO> noticeDTOs = new ArrayList<>();

        for (NoticeEntity entity : noticeEntities) {
            noticeDTOs.add(entity.toNoticeSummaryDTO());
        }

        return noticeDTOs;
    }

    public NoticeDetailDTO searchNoticeById(String id) {
        NoticeEntity noticeEntity = noticeRepository.findNoticeEntityById(id);

        return noticeEntity.toNoticeDetailDTO();
    }

    // Delete (Using ID)

    /**
     * false 오류에 대한 정확한 메시지 출력 불가능. refactoring 필요
     * @param id
     * @return
     */
    public boolean deleteNotice(String id) {
        if (noticeRepository.existsById(id)) {
            noticeRepository.deleteById(id);
            return true;
        } else { return false; }
    }

    // Update (Using ID)
    public NoticeDetailDTO updateNotice(String id, NoticeDetailDTO noticeDetailDTO) {
        Optional<NoticeEntity> optionalEntity = noticeRepository.findById(id);
        // 작성자 id도 판별 할 것?
        if (optionalEntity.isPresent()) {
            NoticeEntity item = optionalEntity.get();

            item.setTitle(noticeDetailDTO.getTitle());
            item.setContent(noticeDetailDTO.getContent());
            noticeRepository.save(item);
            return item.toNoticeDetailDTO();
        } else { return null; }
    }

}