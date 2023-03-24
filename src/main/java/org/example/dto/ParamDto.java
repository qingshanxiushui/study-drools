package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamDto {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "enable")
    private Integer enable;

    @JsonProperty(value = "chinese")
    private String chinese;
}
