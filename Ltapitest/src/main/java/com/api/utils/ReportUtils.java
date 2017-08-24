package com.api.utils;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ReportUtils {
    // report sql
    public static final String INSERT_REPORT="";
    private String projectName;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ReportUtils(){

    }
    public ReportUtils(String projectName){
        this.projectName = projectName;
        this.tag = String.valueOf(System.currentTimeMillis());
    }
    public static JdbcTemplate getJdbcTemplate(){
        Properties prop = new Properties();
        DataSource dataSource =null;
        try {
            prop.load(ReportUtils.class.getResourceAsStream("/report.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
