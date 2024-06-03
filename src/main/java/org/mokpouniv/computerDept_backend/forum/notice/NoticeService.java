package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {

    // notice에서 사용하므로 notice의 기능만을 사용하도록 이름 명시를 해야하나?
    private final NoticeRepository noticeRepository;

    /**
     * id를 uuid를 이용해 난수 문자열 생성하여 저장.
     * @param noticeDetailDTO
     * @return
     */
    public String saveNotice(NoticeDetailDTO noticeDetailDTO) {
        NoticeEntity noticeEntity = NoticeMapper.toEntity(noticeDetailDTO);

        noticeRepository.save(noticeEntity);
        return noticeEntity.getId();
    }

    /**
     * 될라나? 이상한데
     * @param page
     * @return
     */
    public Page<NoticeSummaryDTO> findAllNotice(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("posted_time"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//        return photoRepository.findAll(pageable)
//                .map(PhotoMapper :: toDTO);
        return noticeRepository.findAllbyPage(Pageable.ofSize(10)).map(NoticeMapper :: toSummaryDTO);
    }

    /**
     * 제목을 포함하는 작성글 모두 반환
     * @param title
     * @return
     */
    public Page<NoticeSummaryDTO> findNoticeByTitle(int page, String title) {

        return noticeRepository.findAllNoticeEntityByTitle(title)
                .map(NoticeMapper :: toSummaryDTO);
    }

    public Page<NoticeSummaryDTO> findNoticeByAuthor(int page, String author) {
        return noticeRepository.findAllNoticeEntityByAuthor(author)
                .map(NoticeMapper :: toSummaryDTO);
    }

    public NoticeDetailDTO findNoticeById(String id) {
        NoticeEntity noticeEntity = noticeRepository.findNoticeEntityById(id);

        return NoticeMapper.toDetailDTO(noticeEntity);
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
            return NoticeMapper.toDetailDTO(item);
        } else { return null; }
    }

}