package com.sumit.services;

import com.sumit.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReview(long bookId){
        var reviewList = List.of(
                new Review(1,bookId,9.2,"Good Book"),
                new Review(2,bookId,8.6,"Worth Reading")
        );

        return Flux.fromIterable(reviewList);
    }
}
