package com.example.jpahomework.controller;

import com.example.jpahomework.models.BookRequest;
import com.example.jpahomework.models.dto.Response.GetResponse;
import com.example.jpahomework.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return GetResponse.responseAll(
                "Successfully get all books",
                bookRepository.findAllBooks(),
                bookRepository.findAllBooks().size()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable UUID id) {
        return GetResponse.responseModify(
                "Successfully get book",
                bookRepository.findBookById(id)
        );
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequest body) {
        return GetResponse.responseOnce(
                "Successfully create new book",
                bookRepository.insertBook(body)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookByTitle(@RequestParam("title") String title) {
        return GetResponse.responseModify(
          "Successfully get book",
          bookRepository.findBookByTitle(title)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable(name = "id") UUID id) {
        return GetResponse.responseModify(
                "Successfully delete book",
                bookRepository.deleteBookById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookById(@PathVariable(name = "id") UUID id, @RequestBody BookRequest body) {
        return GetResponse.responseModify(
          "Successfully update book",
          bookRepository.updateBookById(id, body)
        );
    }

}
