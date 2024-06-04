package org.mokpouniv.computerDept_backend.forum.photo;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.file.FileDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoEntity {
    private String id;

    private String title;

    private String author;

    private List<FileDTO> images;

    private int view;

    private LocalDateTime posted_time;
}
