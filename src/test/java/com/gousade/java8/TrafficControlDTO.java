package com.gousade.java8;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TrafficControlDTO implements Serializable {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private KnowledgeBaseResourceInfo resInfo;

    public static void main(String[] args) {
        TrafficControlDTO dto = new TrafficControlDTO();
        dto.setResInfo(new KnowledgeBaseResourceInfo());
    }

    public KnowledgeBaseResourceInfo getResInfo() {
        return resInfo;
    }

    public void setResInfo(KnowledgeBaseResourceInfo resInfo) {
        this.resInfo = resInfo;
    }
}
