package com.example.amd_project.Domain.Dashboard.DTO;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDataDTO implements Serializable {

    @Id
    private String id;
    private String fileId;
    private String fileName;
    private int fileSize;
    private String fileHash;
    private boolean isVuln;
    private Date uploadedAt;

    @Builder
    public ResponseDataDTO(String id, String fileId, String fileName, int fileSize, String fileHash, boolean isVuln, String uploadedAtStr) throws ParseException {
        this.id = id;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileHash = fileHash;
        this.isVuln = isVuln;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.uploadedAt = dateFormat.parse(uploadedAtStr);
    }
}
