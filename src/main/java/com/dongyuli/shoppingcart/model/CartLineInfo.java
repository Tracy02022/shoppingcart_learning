package com.dongyuli.shoppingcart.model;

import com.dongyuli.shoppingcart.entity.Product;

public class CartLineInfo {

    private ProductInfo productInfo;
    private int quantity;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private int amount;


    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public CartLineInfo() {
        this.quantity = 0;
    }

    public Product getProductInfo() {

        return new Product();
    }
}
