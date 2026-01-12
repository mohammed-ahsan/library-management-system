package com.sisimpur.library.service;

import com.sisimpur.library.dto.BookRequest;
import com.sisimpur.library.exception.ResourceNotFoundException;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import com.sisimpur.library.repository.AuthorRepository;
import com.sisimpur.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    public Book createBook(BookRequest bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookRequest.getAuthorId()));

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setGenre(bookRequest.getGenre());
        book.setPublishedYear(bookRequest.getPublishedYear());
        book.setIsAvailable(true);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, BookRequest bookRequest) {
        Book book = getBook(id);
        
        if (bookRequest.getAuthorId() != null) {
            Author author = authorRepository.findById(bookRequest.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookRequest.getAuthorId()));
            book.setAuthor(author);
        }
        
        if (bookRequest.getTitle() != null) {
            book.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getGenre() != null) {
            book.setGenre(bookRequest.getGenre());
        }
        if (bookRequest.getPublishedYear() != null) {
            book.setPublishedYear(bookRequest.getPublishedYear());
        }

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
    }

    public List<Book> searchBooks(String title, String authorName, String genre, 
                                  Integer publishedYear, Boolean isAvailable) {
        return bookRepository.searchBooks(title, authorName, genre, publishedYear, isAvailable);
    }
}
