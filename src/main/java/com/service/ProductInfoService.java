package com.service;

import com.CommonUtils;
import com.domain.ProductInfo;
import com.domain.ProductOption;

import java.util.List;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
        CommonUtils.delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99));
        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();
    }
}
