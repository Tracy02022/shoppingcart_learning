package com.dongyuli.shoppingcart.dao;

import com.dongyuli.shoppingcart.entity.Product;
import com.dongyuli.shoppingcart.model.PaginationResult;
import com.dongyuli.shoppingcart.model.ProductInfo;

public interface ProductDAO {

    public Product findProduct(String code);

    public ProductInfo findProductInfo(String code);

    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                                                       int maxNavigationPage);
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                                                       int maxNavigationPage, String name);

    public void save(ProductInfo productInfo);
}
