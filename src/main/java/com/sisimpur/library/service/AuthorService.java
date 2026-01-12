package com.sisimpur.library.service;

import com.sisimpur.library.exception.ResourceNotFoundException;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setName(authorDetails.getName());
        author.setBio(authorDetails.getBio());
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
