package com.main;


import com.LoggerUtil;
import com.domain.Product;
import com.domain.ProductInfo;
import com.domain.Review;
import com.service.ProductInfoService;
import com.service.ReviewService;

import static com.CommonUtils.stopWatch;
import static com.LoggerUtil.log;


public class ProductService {

    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call
        // => Total Time Taken : 2009

        stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
