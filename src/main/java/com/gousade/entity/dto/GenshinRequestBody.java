package com.gousade.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenshinRequestBody {
    @JsonProperty("role_id")
    private String uid;
    private String server;
}
