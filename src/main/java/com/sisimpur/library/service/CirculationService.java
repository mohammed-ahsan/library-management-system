package com.sisimpur.library.service;

import com.sisimpur.library.dto.BorrowRequest;
import com.sisimpur.library.dto.ReturnRequest;
import com.sisimpur.library.exception.BookNotAvailableException;
import com.sisimpur.library.exception.ResourceNotFoundException;
import com.sisimpur.library.model.Book;
import com.sisimpur.library.model.Circulation;
import com.sisimpur.library.model.User;
import com.sisimpur.library.repository.BookRepository;
import com.sisimpur.library.repository.CirculationRepository;
import com.sisimpur.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CirculationService {

    private final CirculationRepository circulationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public List<Circulation> borrowBooks(BorrowRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        List<Circulation> circulations = new ArrayList<>();

        for (Long bookId : request.getBookIds()) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

            // Check if book is available
            if (!book.getIsAvailable()) {
                throw new BookNotAvailableException("Book '" + book.getTitle() + "' is not available");
            }

            // Check if book is already borrowed and not returned
            circulationRepository.findActiveCirculationByBookId(bookId)
                    .ifPresent(c -> {
                        throw new BookNotAvailableException("Book '" + book.getTitle() + "' is already borrowed");
                    });

            // Create circulation record
            Circulation circulation = new Circulation();
            circulation.setUser(user);
            circulation.setBook(book);
            circulation.setBorrowDate(LocalDateTime.now());

            // Update book availability
            book.setIsAvailable(false);
            bookRepository.save(book);

            circulations.add(circulationRepository.save(circulation));
        }

        return circulations;
    }

    @Transactional
    public List<Circulation> returnBooks(ReturnRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        List<Circulation> returnedCirculations = new ArrayList<>();

        for (Long bookId : request.getBookIds()) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

            // Find active circulation for this book
            Circulation circulation = circulationRepository.findActiveCirculationByBookId(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No active borrowing found for book '" + book.getTitle() + "'"));

            // Verify that the user returning the book is the one who borrowed it
            if (!circulation.getUser().getId().equals(user.getId())) {
                throw new IllegalStateException("Book '" + book.getTitle() + "' was not borrowed by this user");
            }

            // Update circulation record
            circulation.setReturnDate(LocalDateTime.now());
            circulationRepository.save(circulation);

            // Update book availability
            book.setIsAvailable(true);
            bookRepository.save(book);

            returnedCirculations.add(circulation);
        }

        return returnedCirculations;
    }

    public List<Circulation> getUserCirculations(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return circulationRepository.findAllByUserId(userId);
    }

    public List<Circulation> getUserActiveCirculations(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return circulationRepository.findActiveCirculationsByUserId(userId);
    }
}
