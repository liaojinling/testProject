package com.api.constant;

/**
 * Created by owen on 2017-7-14.
 */
public class TestSql {
    public static final String queryCardInfoByCardNumber = "select * from careInfo where cardNumber=?";
    public static final String updateCardInfoByCardNumber = "update careInfo set cardBalance=? where cardNumber=?";
}
