package com.azriasat.reactiveprogramming.service;

import com.azriasat.reactiveprogramming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {
    public Flux<BookInfo> getBooks(){
        var books = List.of(
                new BookInfo(1L,"book One","Author One", "93932333"),
                new BookInfo(2L,"book Two","Author Two", "93932333"),
                new BookInfo(3L, "book three", "Author three", "93932333"),
                new BookInfo(4L,"book four","Author four", "93932333")
        );

return Flux.fromIterable(books);
}

public Mono<BookInfo> getBookById(Long bookId){
        var book = new BookInfo(bookId,"book One","Author four", "93932333");
        return Mono.just(book);
}


}
