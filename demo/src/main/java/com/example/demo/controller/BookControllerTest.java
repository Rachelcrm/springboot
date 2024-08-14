package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BookControllerTest {

    private BookController bookController;

    public static void main(String[] args) {
        BookControllerTest test = new BookControllerTest();
        test.setUp();
        test.testGetAllBooks();
        test.testCreateBook();
        test.testGetBookById();
        test.testDeleteBook();
    }

    // Manual setup method
    public void setUp() {
        bookController = new BookController();
        System.out.println("Setup completed.");
    }

    // Test method for getting all books
    public void testGetAllBooks() {
        List<BookDTO> books = bookController.getAllBooks();
        assert books.isEmpty() : "Books list should be empty";
        System.out.println("testGetAllBooks passed.");
    }

    // Test method for creating a book
    public void testCreateBook() {
        BookDTO book = new BookDTO(1L, "Book Title", "Author Name");
        ResponseEntity<BookDTO> response = bookController.createBook(book);
        assert response.getStatusCode() == HttpStatus.CREATED : "Expected status code 201";
        assert response.getBody() != null && "Book Title".equals(response.getBody().getTitle()) : "Book title should match";
        System.out.println("testCreateBook passed.");
    }

    // Test method for getting a book by ID
    public void testGetBookById() {
        BookDTO book = new BookDTO(1L, "Book Title", "Author Name");
        bookController.createBook(book);
        ResponseEntity<BookDTO> response = bookController.getBookById(1L);
        assert response.getStatusCode() == HttpStatus.OK : "Expected status code 200";
        assert response.getBody() != null && response.getBody().getId().equals(1L) : "Book ID should match";
        System.out.println("testGetBookById passed.");
    }

    // Test method for deleting a book
    public void testDeleteBook() {
        BookDTO book = new BookDTO(1L, "Book Title", "Author Name");
        bookController.createBook(book);
        ResponseEntity<Void> response = bookController.deleteBook(1L);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT : "Expected status code 204";
        assert bookController.getAllBooks().isEmpty() : "Books list should be empty after deletion";
        System.out.println("testDeleteBook passed.");
    }
}
