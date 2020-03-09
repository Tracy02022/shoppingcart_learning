package com.dongyuli.shoppingcart.dao.impl;

import com.dongyuli.shoppingcart.dao.OrderDAO;
import com.dongyuli.shoppingcart.dao.ProductDAO;
import com.dongyuli.shoppingcart.entity.Order;
import com.dongyuli.shoppingcart.entity.OrderDetail;
import com.dongyuli.shoppingcart.entity.Product;
import com.dongyuli.shoppingcart.model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderDAOImpl implements OrderDAO {


    @Autowired
    private SessionFactory sessionFactory;



    @Autowired
    private ProductDAO productDAO;

    private int getMaxOrderNum() {

        String sql = String.format("select max(o.orderNum) from %s", Order.class.getName());
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();

        if (value == null)
            return 0;

        return value;
    }

    @Override
    public void saveOrder(CartInfo cartInfo) {

        Session session = sessionFactory.getCurrentSession();

        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();

        order.setId(UUID.randomUUID().toString());
        order.setOrderNum(orderNum);
        order.setOrderDate(new Date());
        order.setAmount(cartInfo.getAmountTotal());

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());
        order.setCustomerAddress(customerInfo.getAddress());
        order.setCustomerEmail(customerInfo.getEmail());
        order.setCustomerPhone(customerInfo.getPhone());

        session.persist(order);

        List<CartLineInfo> lines = cartInfo.getCartLineInfoList();

        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setAmount(line.getProductInfo().getPrice());
            detail.setQuantity(line.getQuantity());

            String code = line.getProductInfo().getCode();
            Product product = this.productDAO.findProduct(code);
            detail.setProduct(product);

            session.persist(detail);

            //Set OrderNum for report
            cartInfo.setOrderNum(orderNum);

        }


    }

    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()//
                        + "(ord.id, ord.orderDate, ord.orderNum, ord.amount,"
                        + " ord.customerName, ord.customerAddress, ord.customerEmail from "
                        + Order.class.getName() + " ord "
                        + " order by ord.orderNum desc";

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);


        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }

    public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Order.class);
        criteria.add(Restrictions.eq("id", orderId));
        return (Order) criteria.uniqueResult();
    }


    @Override
    public OrderInfo getOrderInfo(String orderId) {

        Order order = this.findOrder(orderId);
        if (order == null)
            return null;
        return new OrderInfo(order.getId(),order.getOrderDate(),
                order.getOrderNum(), order.getAmount(),order.getCustomerName(),
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {

        String sql = String.format("Select new %s(d.id, d.product.code, d.product.name, d.quantity, d,price, d.amount ) from %s d  where d.order.id = :orderId ", OrderDetailInfo.class.getName(), OrderDetail.class.getName());

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);

        return query.list();
    }
}
