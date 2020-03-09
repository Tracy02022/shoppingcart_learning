package com.dongyuli.shoppingcart.dao.impl;

import com.dongyuli.shoppingcart.dao.AccountDAO;
import com.dongyuli.shoppingcart.entity.Account;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account findAccount(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = ((Session) session).createCriteria(Account.class);
        criteria.add(Restrictions.eq("userName", userName));
        return (Account) criteria.uniqueResult();
    }
}
