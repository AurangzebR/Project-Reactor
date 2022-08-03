package com.azriasat.reactiveprogramming.service;

import com.azriasat.reactiveprogramming.domain.Reviews;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Reviews> getAllReviews(Long bookId){
        List<Reviews> review;
        review = List.of(
                new Reviews(2L,bookId,9.1,"dffsdfdfdfdddd"),
                new Reviews(3L,bookId,8.2,"lkdjfdkfkdljflkj")
        );
        return Flux.fromIterable(review);
    }
}
