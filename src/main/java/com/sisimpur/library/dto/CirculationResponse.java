package com.sisimpur.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CirculationResponse {
    private Long id;
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookTitle;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
