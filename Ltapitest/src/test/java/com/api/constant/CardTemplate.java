package com.api.constant;

import com.api.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/7/14.
 */
public class CardTemplate {
    public static JdbcTemplate getJdbcTemplate(){
        String user = "javatest";
        String pwd = "123456";
        String dburl = "jdbc:mysql://115.28.108.130:3306/longtengserver";
        JdbcUtils jdbcUtils = new JdbcUtils(DBType.MYSQLDRIVER,dburl,user,pwd);
        DataSource dataSource = jdbcUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
