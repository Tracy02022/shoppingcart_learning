package com.dongyuli.shoppingcart.dao;

import com.dongyuli.shoppingcart.model.CartInfo;
import com.dongyuli.shoppingcart.model.OrderDetailInfo;
import com.dongyuli.shoppingcart.model.OrderInfo;
import com.dongyuli.shoppingcart.model.PaginationResult;

import java.util.List;

public interface OrderDAO {

    public void saveOrder(CartInfo cartInfo);

    public PaginationResult<OrderInfo> listOrderInfo(int page,
                                                     int maxResult,
                                                     int maxNavigationPage);

    public OrderInfo getOrderInfo(String orderId);

    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
