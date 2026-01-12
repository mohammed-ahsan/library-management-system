package com.sisimpur.library.repository;

import com.sisimpur.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b WHERE " +
           "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:authorName IS NULL OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :authorName, '%'))) AND " +
           "(:genre IS NULL OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :genre, '%'))) AND " +
           "(:publishedYear IS NULL OR b.publishedYear = :publishedYear) AND " +
           "(:isAvailable IS NULL OR b.isAvailable = :isAvailable)")
    List<Book> searchBooks(@Param("title") String title,
                          @Param("authorName") String authorName,
                          @Param("genre") String genre,
                          @Param("publishedYear") Integer publishedYear,
                          @Param("isAvailable") Boolean isAvailable);
}
