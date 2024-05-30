package org.mokpouniv.computerDept_backend.forum.photo;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.file.FileDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoSummaryDTO {
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String title;

    private String author;

    private FileDTO thumbnail;

    @Builder.Default
    private int view = 0;

    @Builder.Default
    private LocalDateTime posted_time = LocalDateTime.now();
}
