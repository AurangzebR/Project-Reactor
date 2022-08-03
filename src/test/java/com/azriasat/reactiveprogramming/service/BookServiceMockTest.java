package com.azriasat.reactiveprogramming.service;

import com.azriasat.reactiveprogramming.exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.invocation.RealMethod;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookInfoService bookInfoService;
    @Mock
    private ReviewService reviewService;

    @Test
    void getBooks() {
        Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
        Mockito.when(reviewService.getAllReviews(Mockito.anyLong())).thenCallRealMethod();
        var books= bookService.getBooks();
        StepVerifier.create(books).expectNextCount(4L).verifyComplete();
    }
    @Test
    void getBooksOnError() {
        Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
        Mockito.when(reviewService.getAllReviews(Mockito.anyLong())).thenThrow(new IllegalStateException("exception using test"));
        var books= bookService.getBooks();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
    @Test
    void getBooksOnErrorRetry() {
        Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
        Mockito.when(reviewService.getAllReviews(Mockito.anyLong())).thenThrow(new IllegalStateException("exception using test"));
        var books= bookService.getBooksByRetry();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
    @Test
    void getBooksOnErrorRetryWhen() {
        Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
        Mockito.when(reviewService.getAllReviews(Mockito.anyLong())).thenThrow(new IllegalStateException("exception using test"));
        var books= bookService.getBooksByRetryWhen();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }

}