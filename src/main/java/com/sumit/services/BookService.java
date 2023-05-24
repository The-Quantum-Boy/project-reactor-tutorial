package com.sumit.services;

import com.sumit.domain.Book;
import com.sumit.domain.BookInfo;
import com.sumit.domain.Review;
import com.sumit.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

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

         var allBooks=bookInfoService.getbooks();

         return allBooks
                 .flatMap(bookInfo -> {
                     Mono<List<Review>> reviews=reviewService.getReview(bookInfo.getBookId()).collectList();
                     return reviews.map(review->new Book(bookInfo,review));
                 })
                 .onErrorMap(throwable -> {
                     log.error("Exception is : "+throwable);
                     return new BookException("Exception occured while fetching Books");
                 }).log();
    }


    public Flux<Book> getBooksRetry(){

        var allBooks=bookInfoService.getbooks();

        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews=reviewService.getReview(bookInfo.getBookId()).collectList();
                    return reviews.map(review->new Book(bookInfo,review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : "+throwable);
                    return new BookException("Exception occured while fetching Books");
                })
                .retry(3).log();
    }


    public Flux<Book> getBooksRetryWhen(){

        var retrySpecs= Retry.backoff(3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())));

        var allBooks=bookInfoService.getbooks();

        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews=reviewService.getReview(bookInfo.getBookId()).collectList();
                    return reviews.map(review->new Book(bookInfo,review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : "+throwable);
                    return new BookException("Exception occured while fetching Books");
                })
                .retryWhen(retrySpecs).log();
    }

    public Mono<Book> getBookById(long bookId){
        var book=bookInfoService.getBookById(bookId);
        var review=reviewService.getReview(bookId).collectList();

        return book.zipWith(review,(b,r)->new Book(b,r)).log();


    }
}
