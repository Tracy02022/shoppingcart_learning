package com.dongyuli.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {

    private int orderNum;

    private int orderAmount;

    private CustomerInfo customerInfo;

    private final List<CartLineInfo> cartLineInfoList = new ArrayList<CartLineInfo>();


    public List<CartLineInfo> getCartLineInfoList() {
        return cartLineInfoList;
    }

    private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLineInfoList) {
            if (line.getProductInfo().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }


    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }


    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo lineInfo : this.cartLineInfoList) {
            quantity += lineInfo.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo lineInfo : this.cartLineInfoList) {
            total += lineInfo.getAmount();
        }
        return total;
    }

    public void setAmountTotal(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void addProduct(ProductInfo productInfo, int i) {

    }

    public void removeProduct(ProductInfo productInfo) {
    }

    public void updateQuantity(CartInfo cartForm) {
        if(cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLineInfoList();
            for (CartLineInfo lineInfo : lines) {
                this.updateProduct(lineInfo.getProductInfo().getCode(), 1);
            }
        }
    }

    private void updateProduct(String code, int i) {
    }

    public boolean isEmpty() {
        if(cartLineInfoList == null)
            return true;
        return false;
    }

    public boolean isValidCustomer() {

        return false;
    }
}
