package com.sisimpur.library.repository;

import com.sisimpur.library.model.Circulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CirculationRepository extends JpaRepository<Circulation, Long> {
    
    @Query("SELECT c FROM Circulation c WHERE c.book.id = :bookId AND c.returnDate IS NULL")
    Optional<Circulation> findActiveCirculationByBookId(@Param("bookId") Long bookId);
    
    @Query("SELECT c FROM Circulation c WHERE c.user.id = :userId AND c.returnDate IS NULL")
    List<Circulation> findActiveCirculationsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT c FROM Circulation c WHERE c.user.id = :userId")
    List<Circulation> findAllByUserId(@Param("userId") Long userId);
}
