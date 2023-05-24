package com.sumit.services;

import com.sumit.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getbooks(){
        var books= List.of(
                new BookInfo(1,"Book one","Author one","1234546"),
                new BookInfo(2,"Book two","Author two","3546754"),
                new BookInfo(3,"Book three","Author three","5687936")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId){
        var book=new BookInfo(bookId,"Book one","Author one","23453565");
        return Mono.just(book);
    }


}
