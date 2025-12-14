package com.example.backend.controller;

import com.example.backend.model.Transaction;
import com.example.backend.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionRepository repo;

    public TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Transaction add(@RequestBody Transaction t) {
        if (t.getTime() == null) {
            t.setTime(Instant.now());
        }
        return repo.save(t);
    }

    @GetMapping("/latest")
    public List<Transaction> latest() {
        return repo.findTop100ByOrderByTimeDesc();
    }
}
