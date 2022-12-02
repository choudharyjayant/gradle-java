package com.javabatch2.Training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementDto {
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
