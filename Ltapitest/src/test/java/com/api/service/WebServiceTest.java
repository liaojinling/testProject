package com.api.service;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * Created by yingj on 2017-7-20.
 */
public class WebServiceTest {
    private static String endPoint = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx";
    private static String targetNameSpace = "http://WebXml.com.cn/";
    private static String wsHost = "http://WebXml.com.cn/getCountryCityByIp";

    @Test
    public void testWebService(){
        WebService webService = RPCServiceFactory.createWebService();
        webService.createClientCode("com.test",endPoint+"?wsdl");

    }

    public static void main(String[] args) {
        getCountryCityByIp("115.28.108.130");//调用函数，传递参数是一个ip地址
    }

    public static Call getCall() {//获得Call,放在了一个函数中
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endPoint));
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return call;
    }

    public static void getCountryCityByIp(String theIpAddress) {//这是调用的服务方法
        Call call = getCall();
        call.setOperationName(new QName(targetNameSpace, "getCountryCityByIp"));
        call.setUseSOAPAction(true);
        call.setSOAPActionURI(wsHost);
        call.addParameter(new QName(targetNameSpace, "theIpAddress"),
                org.apache.axis.encoding.XMLType.XSD_STRING,
                javax.xml.rpc.ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        try {
            System.out.println(call.invoke(new Object[]{theIpAddress}));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            String str[] = (String[]) call.invoke(new Object[]{theIpAddress});
            for (int i = 0; i < str.length; i++) {
                System.out.println(str[i]);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}