package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private List<BookDTO> books = new ArrayList<>();

	// GET all books
	@GetMapping
	public List<BookDTO> getAllBooks() {
		return books;
	}

	// GET a book by ID
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		for (BookDTO book : books) {
			if (book.getId().equals(id)) {
				return ResponseEntity.ok(book);
			}
		}
		return ResponseEntity.notFound().build();
	}

	// POST a new book
	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
		books.add(bookDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
	}

	// DELETE a book
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		books.removeIf(book -> book.getId().equals(id));
		return ResponseEntity.noContent().build();
	}
}