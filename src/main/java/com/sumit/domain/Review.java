package com.sumit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private long reviewId;

    private long bookId;

    private double rating ;

    private String comments;



}
