package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {

    private String fileUrl;

    private String fileName;

    private String fileNameExtension;


}
