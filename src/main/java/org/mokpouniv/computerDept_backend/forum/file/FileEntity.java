package org.mokpouniv.computerDept_backend.forum.file;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity {
    private int number;

    private String fileUrl;

    private String fileName;
}
