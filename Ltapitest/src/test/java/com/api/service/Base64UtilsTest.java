package com.api.service;

import com.api.utils.Base64Utils;
import org.junit.Test;

/**
 * Created by Administrator on 2017/7/14.
 */
public class Base64UtilsTest {
    private Base64Utils base64Utils = new Base64Utils();
    @Test
    public void testEncodeBase64(){
        try {
            String encode = base64Utils.encodeBase64("test01".getBytes());
            System.out.println(encode);
            byte[] decode = base64Utils.decodeBase64(encode);
            System.out.println(new String(decode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
