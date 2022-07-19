package com.service;

import com.CommonUtils;
import com.domain.Review;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        CommonUtils.delay(1000);
        return new Review(200, 4.5);
    }
}
