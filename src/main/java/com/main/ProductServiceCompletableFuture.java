package com.main;


import com.LoggerUtil;
import com.domain.Product;
import com.domain.ProductInfo;
import com.domain.Review;
import com.service.ProductInfoService;
import com.service.ReviewService;

import java.util.concurrent.CompletableFuture;

import static com.CommonUtils.stopWatch;
import static com.LoggerUtil.log;


public class ProductServiceCompletableFuture {

    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> cfProductInfo =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> cfReview =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = cfProductInfo
                .thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        // => Total Time Taken : 1029
        stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceCompletableFuture productService = new ProductServiceCompletableFuture(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
