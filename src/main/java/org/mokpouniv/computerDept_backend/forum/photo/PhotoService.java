package org.mokpouniv.computerDept_backend.forum.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    /**
     * testing method
     * @return
     */
    public Page<PhotoSummaryDTO> getAll(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("posted_time"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return photoRepository.findAll(pageable)
                .map(PhotoMapper :: toSummaryDTO);
    }

    public PhotoSummaryDTO createPhoto(PhotoDetailDTO photoDetailDTO) {
        PhotoEntity resultEntity = photoRepository.save(PhotoMapper.toEntity(photoDetailDTO));
        return PhotoMapper.toSummaryDTO(resultEntity);
    }

    public PhotoDetailDTO findById(String id) {
        Optional<PhotoEntity> photo = photoRepository.findById(id);
        return photo.map(PhotoMapper::toDetailDTO).orElse(null);
    }
}
