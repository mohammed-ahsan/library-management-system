package com.sisimpur.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequest {
    private Long userId;
    private List<Long> bookIds;
}
