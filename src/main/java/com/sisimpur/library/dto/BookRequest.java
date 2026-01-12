package com.sisimpur.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private Long authorId;
    private String genre;
    private Integer publishedYear;
}
