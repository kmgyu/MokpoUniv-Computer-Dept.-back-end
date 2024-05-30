package org.mokpouniv.computerDept_backend.forum.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    /**
     * testing method
     * @return
     */
    public List<PhotoSummaryDTO> getAll() {
        return photoRepository.findAll().stream()
                .map(PhotoMapper :: toDTO).collect(Collectors.toList());
    }

    public PhotoSummaryDTO createPhoto(PhotoSummaryDTO photoSummaryDTO) {
        PhotoEntity resultEntity = photoRepository.save(PhotoMapper.toEntity(photoSummaryDTO));
        return PhotoMapper.toDTO(resultEntity);
    }
}
