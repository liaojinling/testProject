package com.apitest;

import com.api.asserts.ApiSimpleAssert;
import com.api.service.HttpClientService;
import com.api.service.RPCServiceFactory;
import com.api.utils.JdbcUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * Created by yingj on 2017-8-4.
 */
public class JenkinsTest {
    private Logger logger = LoggerFactory.getLogger(JenkinsTest.class);
    private static JdbcTemplate jdbcTemplate;
    private static HttpClientService httpClientService;
    private static ApiSimpleAssert apiSimpleAssert;
    @BeforeClass
    public static void beforeClass(){
        jdbcTemplate = JdbcUtils.getJdbcTemplate("report.properties");
        httpClientService = RPCServiceFactory.createHttpService();
        apiSimpleAssert = new ApiSimpleAssert();
    }

    @Test
    public void testApi(){
        Map map = jdbcTemplate.queryForMap("select * from userinfo where username=?",new Object[]{"2"});
        System.out.println(map);
        String response = httpClientService.sendHttpGet("http://www.baidu.com/s?wd=123");
        // 1.直接断言
        // 2.反序列化，再断言
        System.out.println(response);
        apiSimpleAssert.apiAssertEquals("qqq","qqq","longteng","testApi()");
    }





    @Test
    public void testJenkins01(){
        logger.info("testJenkins01 is running...");
        System.out.println("testJenkins01 is running...");
        Assert.assertNotEquals(1234,123);
    }

    @Test
    public void testJenkins02(){
        logger.info("testJenkins02 is running...");
        System.out.println("testJenkins02 is running...");
        Assert.assertNotEquals(1234,123);
    }

    @Test
    public void testJenkins03(){
        logger.info("testJenkins03 is running...");
        System.out.println("testJenkins03 is running...");
        Assert.assertNotEquals(1234,123);
    }

    @Test
    public void testJenkins04(){
        logger.info("testJenkins04 is running...");
        System.out.println("testJenkins04 is running...");
        Assert.assertNotEquals(1234,123);
    }
}
