package org.mokpouniv.computerDept_backend.forum.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping("/create")
    public ResponseEntity<PhotoSummaryDTO> create(@RequestBody PhotoSummaryDTO photoSummaryDTO) {
        PhotoSummaryDTO result = photoService.createPhoto(photoSummaryDTO);
        return ResponseEntity.created(URI.create("/photo/" + result.getId())).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<PhotoSummaryDTO>> getPhotos() {
        List<PhotoSummaryDTO> results = photoService.getAll();
        return ResponseEntity.ok(results);
    }

}
