package com.api.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.api.asserts.ApiSimpleAssert;
import com.api.constant.CardTemplate;
import com.api.constant.TestSql;
import com.api.utils.Base64Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * Created by owen on 2017-7-13.
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class HttpClientTest {
    private Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
    private String url = "http://115.28.108.130:8080/gasStation/process";
    private HttpClientService httpClientService = null;
    private JdbcTemplate jdbcTemplate = null;
    private ApiSimpleAssert apiSimpleAssert = null;

    @Before
    public void setUp() throws Exception {
        apiSimpleAssert = new ApiSimpleAssert();
        jdbcTemplate = CardTemplate.getJdbcTemplate();
        httpClientService = RPCServiceFactory.createHttpService();
    }

    @After
    public void tearDown() throws Exception {

    }

    //绑卡
    @Test
    public void testSendPost_BindCard_Success() {
        logger.info("testSendPost_BindCard_Success....");
        String url = "http://115.28.108.130:8080/gasStation/process";
        JSONObject reqJson = new JSONObject();
        reqJson.put("dataSourceId", "dGVzdDAx");
        reqJson.put("methodId", "01A");
        JSONObject cardUserJson = new JSONObject();
        cardUserJson.put("userId", "123456");
        cardUserJson.put("userName", "owen");
        cardUserJson.put("idType", "1");
        cardUserJson.put("idNumber", "110101198804128194");
        cardUserJson.put("email", "owen@123.com");
        cardUserJson.put("gender", "0");
        JSONObject cardInfoJson = new JSONObject();
        cardInfoJson.put("cardNumber", "long001");
        reqJson.put("cardUser", cardUserJson);
        reqJson.put("cardInfo", cardInfoJson);
        System.out.println("reqJson.toJSONString():" + reqJson);
        JSONObject res = httpClientService.sendHttpPost(url, reqJson, false);
        System.out.println("res:" + res.toString());
    }

    //查询接口
    @Test
    public void testSendGet_Query_Success() {
        logger.info("testSendGet_Query_Success....");
        String dataSourceId = Base64Utils.encodeBase64("test01".getBytes());
        logger.info("dataSourceId:" + dataSourceId);
        String parameters = "?methodId=02A&userId=1008&cardNumber=long001&dataSourceId=" + dataSourceId;
        String response = httpClientService.sendHttpGet(url + parameters);
        JSONObject respJson = JSONObject.fromObject(response);
        JSONObject resultJson = JSONObject.fromObject(respJson.get("result").toString());
        JSONArray consumptionDetailsJson = JSONArray.fromObject(resultJson.get("consumptionDetails").toString());
        System.out.println(resultJson.get("consumptionDetails"));
        System.out.println(resultJson.get("rechargeDetails"));
        System.out.println(response);
        System.out.println("=============================");
        Map<String, String> map = jdbcTemplate.queryForMap(TestSql.queryCardInfoByCardNumber, new Object[]{"long001"});
        System.out.println(map);
    }

    //充值
    @Test
    public void testSendPost_Charge_Success() {
        logger.info("testSendPost_Charge_Success....");
        String dataSourceId = Base64Utils.encodeBase64("test01".getBytes());
        logger.info("dataSourceId:" + dataSourceId);
        JSONObject reqJson = new JSONObject();
        reqJson.put("methodId", "03A");
        reqJson.put("dataSourceId", dataSourceId);
        JSONObject cardInfoJson = new JSONObject();
        cardInfoJson.put("cardNumber", "long001");
        cardInfoJson.put("cardBalance", "666");
        reqJson.put("cardInfo", cardInfoJson);
        JSONObject cardUserJson = new JSONObject();
        cardUserJson.put("userId", "1008");
        reqJson.put("cardUser", cardUserJson);
        JSONObject resp = httpClientService.sendHttpPost(url, reqJson, false);
        System.out.println(resp);
    }

    //消费
    @Test
    public void testSendPost_Consume_Success() {
        logger.info("testSendPost_Consume_Success....");
        JSONObject reqJson = new JSONObject();
        reqJson.put("methodId", "04A");
        reqJson.put("dataSourceId", "dGVzdDAx");
        JSONObject cardInfoJson = new JSONObject();
        cardInfoJson.put("cardNumber", "long001");
        cardInfoJson.put("cardBalance", "10");
        reqJson.put("cardInfo", cardInfoJson);
        JSONObject cardUserJson = new JSONObject();
        cardUserJson.put("userId", "1008");
        reqJson.put("cardUser", cardUserJson);
        JSONObject resp = httpClientService.sendHttpPost(url, reqJson, false);
        System.out.println(resp);
        apiSimpleAssert.apiAssertEquals(false, resp.get("success"));
        apiSimpleAssert.apiAssertEquals("返回码错误", 200, resp.get("code"));
        apiSimpleAssert.apiAssertEquals("消费失败，余额不足！", "消费成功！", resp.get("msg"));

    }

    @Test
    public void testAssert() {

        String encode = Base64Utils.encodeBase64("sadf".getBytes());

        apiSimpleAssert.apiAssertEquals("123", "123", "demo", "testPay()");
        apiSimpleAssert.apiAssertEquals("123", "123", "demo", "testPay()");
        apiSimpleAssert.apiAssertEquals("123", "1234", "demo", "testPay()");
    }

    @Test
    public void testAssert1() {
        apiSimpleAssert.apiAssertEquals("123", "123", "demo", "testPay1()");
        apiSimpleAssert.apiAssertEquals("断言失败！", "123", "123", "demo", "testPay1()");
    }
}
