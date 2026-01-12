package com.sisimpur.library.controller;

import com.sisimpur.library.dto.BookRequest;
import com.sisimpur.library.model.Book;
import com.sisimpur.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer publishedYear,
            @RequestParam(required = false) Boolean isAvailable) {
        
        // If any search parameter is provided, use search, otherwise get all books
        if (title != null || authorName != null || genre != null || 
            publishedYear != null || isAvailable != null) {
            return ResponseEntity.ok(bookService.searchBooks(title, authorName, genre, publishedYear, isAvailable));
        }
        
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(bookRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
