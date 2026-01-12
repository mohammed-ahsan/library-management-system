package com.sisimpur.library.controller;

import com.sisimpur.library.dto.BorrowRequest;
import com.sisimpur.library.dto.ReturnRequest;
import com.sisimpur.library.model.Circulation;
import com.sisimpur.library.service.CirculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/circulation")
@RequiredArgsConstructor
public class CirculationController {

    private final CirculationService circulationService;

    @PostMapping("/borrow")
    public ResponseEntity<List<Circulation>> borrowBooks(@RequestBody BorrowRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(circulationService.borrowBooks(request));
    }

    @PostMapping("/return")
    public ResponseEntity<List<Circulation>> returnBooks(@RequestBody ReturnRequest request) {
        return ResponseEntity.ok(circulationService.returnBooks(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Circulation>> getUserCirculations(@PathVariable Long userId) {
        return ResponseEntity.ok(circulationService.getUserCirculations(userId));
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<Circulation>> getUserActiveCirculations(@PathVariable Long userId) {
        return ResponseEntity.ok(circulationService.getUserActiveCirculations(userId));
    }
}
