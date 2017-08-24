package com.api.utils;

import com.api.constant.DBType;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/14.
 */
public class JdbcUtils {
    private static String driverClassName;
    private static String url;
    private static String user;
    private static String pwd;
    private static DataSource myDataSource = null;

    public JdbcUtils() {

    }

    public JdbcUtils(String driverClassName, String url, String user, String pwd) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    public static Properties readProperties(String propertiesName){
        Properties prop = new Properties();
        try {
            prop.load(JdbcUtils.class.getClassLoader().getResourceAsStream(propertiesName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static JdbcTemplate getJdbcTemplate(String propertiesName) {
        Properties prop  = readProperties(propertiesName);
        JdbcTemplate jdbcTemplate = null;
        try {
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(is);
            myDataSource = BasicDataSourceFactory.createDataSource(prop);
            jdbcTemplate = new JdbcTemplate(myDataSource);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
        return jdbcTemplate;
    }

    public static JdbcTemplate getJdbcTemplate(Properties prop) {
        JdbcTemplate jdbcTemplate = null;
        try {
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(is);
            myDataSource = BasicDataSourceFactory.createDataSource(prop);
            jdbcTemplate = new JdbcTemplate(myDataSource);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
        return jdbcTemplate;
    }

    //获得数据源
    public static DataSource getDataSource() {
        try {
            // 1.注册
            Class.forName(DBType.MYSQLDRIVER);
            Properties prop = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(is);
            prop.put("driverClassName", driverClassName);
            prop.put("url", url);
            prop.put("username", user);
            prop.put("password", pwd);
            //使用的是DBCP方式来加载数据库连接信息
            myDataSource = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
        return myDataSource;
    }
}
