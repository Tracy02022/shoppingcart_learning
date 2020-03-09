package com.dongyuli.shoppingcart.dao;

import com.dongyuli.shoppingcart.entity.Account;

public interface AccountDAO {

    public Account findAccount(String userName);
}
