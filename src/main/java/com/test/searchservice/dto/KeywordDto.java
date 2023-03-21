package com.test.searchservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class KeywordDto {

    private Long idx;

    @NotEmpty(message = "키워드를 입력해주세요.")
    private String keyword;

    private Long cnt;
}
