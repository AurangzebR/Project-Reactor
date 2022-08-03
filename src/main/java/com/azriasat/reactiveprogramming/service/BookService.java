package com.azriasat.reactiveprogramming.service;

import com.azriasat.reactiveprogramming.domain.Book;
import com.azriasat.reactiveprogramming.domain.Reviews;
import com.azriasat.reactiveprogramming.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {
    private BookInfoService bookInfoService;
    private ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks(){
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            Mono<List<Reviews>> reviews =
            reviewService.getAllReviews(bookInfo.getBookId()).collectList();
            return reviews.map(review -> new Book(bookInfo,review));
        }).onErrorMap(throwable -> {
            log.error("Exception is: "+throwable);
            return  new BookException("Exception uccured while fetching books");
                })
                .log();

    }

    public Flux<Book> getBooksByRetry(){
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Reviews>> reviews =
                            reviewService.getAllReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo,review));
                }).onErrorMap(throwable -> {
                    log.error("Exception is: "+throwable);
                    return  new BookException("Exception uccured while fetching books");
                }).retry(3)
                .log();

    }
    public Flux<Book> getBooksByRetryWhen(){
        //var retrySpecs = getRetrySpecs();
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Reviews>> reviews =
                            reviewService.getAllReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo,review));
                }).onErrorMap(throwable -> {
                    log.error("Exception is: "+throwable);
                    return  new BookException("Exception uccured while fetching books");
                }).retryWhen(getRetrySpecs())
                .log();

    }

    private RetryBackoffSpec getRetrySpecs() {
        return Retry.backoff(
                        3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((RetryBackoffSpec, RetrySignal) ->
                        Exceptions.propagate(RetrySignal.failure()));
    }

    public Mono<Book> getBookById(Long bookId){
        var bookInfo = bookInfoService.getBookById(bookId);
        var reviews = reviewService.getAllReviews(bookId).collectList();
        return bookInfo.zipWith(reviews,(b,r)->
            new Book(b,r)
        );
    }
}
