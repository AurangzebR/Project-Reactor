package com.azriasat.reactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();
    private BookService bookService = new BookService(bookInfoService,reviewService);

    @Test
    void getBooks(){
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("book One",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                        })
                .assertNext(book -> { assertEquals("book Two",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());})
                .assertNext(book -> { assertEquals("book three",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());}

                )
                .assertNext(book -> { assertEquals("book four",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());}

                )
                .verifyComplete();

    }

    @Test
    void getBookById(){
        var book = bookService.getBookById(1L);
        StepVerifier.create(book).assertNext(book1 -> {
           assertEquals("book One",book1.getBookInfo().getTitle());
            assertEquals(2,book1.getReviews().size());
        }).verifyComplete();

    }

}