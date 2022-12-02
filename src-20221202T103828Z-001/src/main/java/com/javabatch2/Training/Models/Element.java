package com.javabatch2.Training.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ElementData")
public class Element {
    @Id
    private String workUrn;
    private String entityUrn;
    private String type;
    private String slateManifestUrn;
    private String text;
    private String createdBy;
    private String createdTimestamp;
    private String updatedBy;
    private String updatedTimestamp;
}
