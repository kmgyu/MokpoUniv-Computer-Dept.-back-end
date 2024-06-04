package org.mokpouniv.computerDept_backend.forum.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/photo-gallery")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping("/create")
    public ResponseEntity<PhotoSummaryDTO> create(@RequestBody PhotoDetailDTO photoDetailDTO) {
        PhotoSummaryDTO result = photoService.createPhoto(photoDetailDTO);
        return ResponseEntity.created(URI.create("/photo/" + result.getId())).build();
    }

    @GetMapping("/")
    public ResponseEntity<PagedModel<PhotoSummaryDTO>> searchPhotos(@RequestParam int page) {
        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }
        Page<PhotoSummaryDTO> results = photoService.getAll(page);
        if (results.getNumberOfElements() == 0) {
            return ResponseEntity.noContent().build();
        }
//      for stable Json Serialize, we need to use PagedModel.
        PagedModel<PhotoSummaryDTO> paged = new PagedModel<>(results);
        return ResponseEntity.ok(paged);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDetailDTO> getPhoto(@PathVariable String id) {
        PhotoDetailDTO detailDTO = (photoService.findById(id));
        if (detailDTO != null) {
            return ResponseEntity.ok(detailDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
