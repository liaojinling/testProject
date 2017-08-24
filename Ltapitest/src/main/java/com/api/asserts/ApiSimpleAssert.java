package com.api.asserts;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.api.bean.Report;
import com.api.constant.ReportSql;
import com.api.utils.ReportUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by owen on 2017/7/14.
 */
public class ApiSimpleAssert {
    private Logger logger = LoggerFactory.getLogger(ApiSimpleAssert.class);
    private JdbcTemplate jdbcTemplate = ReportUtils.getJdbcTemplate();

    public String getTimetemp() {
        return timetemp;
    }

    public void setTimetemp(String timetemp) {
        this.timetemp = timetemp;
    }

    private String timetemp;

    public ApiSimpleAssert() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
        this.setTimetemp(time);
    }

    public void apiAssertEquals(Object expected, Object actual) {
        assertEquals(expected, actual);
    }

    public void apiAssertEquals(String msg, Object expected, Object actual) {
        assertEquals(msg, expected, actual);
    }

    public void apiAssertEquals( Object expected, Object actual, String projectName, String testCaseName) {
        List stringReportMap = jdbcTemplate.queryForList(ReportSql.QUERY, new Object[]{projectName, testCaseName, timetemp});
        if (stringReportMap.isEmpty()) {
            jdbcTemplate.update(ReportSql.INSERT, new Object[]{projectName, testCaseName, "", "", "", "", timetemp});
        }
        try {
            assertEquals(expected, actual);
            logger.info("预期值和实际值均为："+actual);
            jdbcTemplate.update(ReportSql.UPDATE, new Object[]{expected, actual,1, projectName, testCaseName, timetemp});
        } catch (AssertionError e) {
            logger.info("断言失败！预期值为："+expected+"， 实际值为："+actual);
            jdbcTemplate.update(ReportSql.UPDATE, new Object[]{expected, actual,0, projectName, testCaseName, timetemp});
            throw new AssertionError();
        }
    }

    public void apiAssertEquals(String msg, Object expected, Object actual, String projectName, String testCaseName) {
        Map<String, Report> stringReportMap = jdbcTemplate.queryForMap(ReportSql.QUERY, new Object[]{projectName, testCaseName, this.timetemp});
        if (stringReportMap.isEmpty()) {
            jdbcTemplate.update(ReportSql.INSERT, new Object[]{projectName, testCaseName, "", "", "", "", timetemp});
        }
        try {
            assertEquals(msg, expected, actual);
            logger.info("预期值和实际值均为："+actual);
            jdbcTemplate.update(ReportSql.UPDATE, new Object[]{expected, actual,1, projectName, testCaseName, timetemp});
        } catch (AssertionError e) {
            logger.info("断言失败！预期值为："+expected+"， 实际值为："+actual);
            jdbcTemplate.update(ReportSql.UPDATE, new Object[]{expected, actual,0, projectName, testCaseName, timetemp});
            throw new AssertionError();
        }
    }


}
