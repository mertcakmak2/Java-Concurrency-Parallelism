package com.main;

import com.domain.Product;
import com.domain.ProductInfo;
import com.domain.Review;
import com.service.ProductInfoService;
import com.service.ReviewService;

import static com.CommonUtils.stopWatch;
import static com.LoggerUtil.log;


public class ProductServiceMultiThread {

    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceMultiThread(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException {
        stopWatch.start();
        ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(productId);
        ReviewRunnable reviewRunnable = new ReviewRunnable(productId);

        Thread productInfoThread = new Thread(productInfoRunnable);
        Thread reviewThread = new Thread(reviewRunnable);

        productInfoThread.start();
        reviewThread.start();

        productInfoThread.join();
        reviewThread.join();

        // => Total Time Taken : 1017

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfoRunnable.productInfo, reviewRunnable.review);
    }

    public static void main(String[] args) throws InterruptedException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceMultiThread productService = new ProductServiceMultiThread(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }

    private class ProductInfoRunnable implements Runnable {
        private String productId;
        private ProductInfo productInfo;

        public ProductInfo getProductInfo() {
            return productInfo;
        }

        public ProductInfoRunnable(String productId) {
            this.productId = productId;
        }

        @Override
        public void run() {

            productInfo = productInfoService.retrieveProductInfo(productId);
        }
    }

    private class ReviewRunnable implements Runnable {
        private String productId;
        private Review review;
        public ReviewRunnable(String productId) {
            this.productId = productId;
        }
        public Review getReview() {
            return review;
        }
        @Override
        public void run() {
            review = reviewService.retrieveReviews(productId);
        }
    }
}
