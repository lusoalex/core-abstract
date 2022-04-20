package com.example.demo.dto.error;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ErrorDTO.ErrorDTOBuilder.class)
public class ErrorDTO {

    String message;
    String status;
    String code;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ErrorDTOBuilder {

    }
}
