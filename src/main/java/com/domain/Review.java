package com.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {
    private int noOfReviews;
    private double overallRating;
}
