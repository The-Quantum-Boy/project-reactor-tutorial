package com.sumit.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {


    private BookInfoService bookInfoService=new BookInfoService();

    private ReviewService reviewService=new ReviewService();

  private BookService bookService= new BookService(bookInfoService,reviewService);

    @Test
    void getBooks() {

        var books=bookService.getBooks();
        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("Book one",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book two",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book three",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                })
                .verifyComplete();

    }

    @Test
    void getBookById() {

        var book=bookService.getBookById(1);
        StepVerifier.create(book)
                .assertNext(b -> {
                    assertEquals("Book one",b.getBookInfo().getTitle());
                    assertEquals(2,b.getReviews().size());
                })
                .verifyComplete();
    }
}