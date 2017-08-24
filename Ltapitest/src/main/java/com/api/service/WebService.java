package com.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yingj on 2017-7-20.
 */
public class WebService {

    public void createClientCode(String packageName, String wsdlurl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("wsimport -s ");
        stringBuffer.append(System.getProperty("user.dir") + "\\src\\test\\java");
        stringBuffer.append(" -p ");
        stringBuffer.append(packageName);
        stringBuffer.append(" ");
        stringBuffer.append(wsdlurl);
        System.out.println(stringBuffer.toString());
        String line = null;
//        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(stringBuffer.toString());
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
