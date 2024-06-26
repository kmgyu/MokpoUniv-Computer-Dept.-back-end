package org.mokpouniv.computerDept_backend.forum.file;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
    private int number;

    private String fileUrl;

    private String fileName;
}
