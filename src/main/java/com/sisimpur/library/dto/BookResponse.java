package com.sisimpur.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String genre;
    private Integer publishedYear;
    private Boolean isAvailable;
    private AuthorResponse author;
}
