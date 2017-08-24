package com.api.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by owen on 2017-7-13.
 */
public class SocketClientService {
    private String address;
    private String port;
    private int timeOut = 300000;
    private String encodeType = "gbk";
    private int BUFFER_SIZE = 1048576;
    private static Logger logger = LoggerFactory.getLogger(SocketClientService.class);

    public void setBUFFER_SIZE(int BUFFER_SIZE) {
        this.BUFFER_SIZE = BUFFER_SIZE;
    }

    public SocketClientService(String address, String port) {
        this.address = address;
        this.port = port;
    }

    public String sendMessage(String message) {
        Socket client = null;
        BufferedOutputStream os = null;
        BufferedInputStream is = null;
        Object bufferedReader = null;
        char[] data = new char[this.BUFFER_SIZE];

        try {
            client = new Socket();
            client.connect(new InetSocketAddress(InetAddress.getByName(this.address), Integer.parseInt(this.port)), this.timeOut);
            client.setSoTimeout(this.timeOut);
            os = new BufferedOutputStream(client.getOutputStream());
            is = new BufferedInputStream(client.getInputStream());
            os.write(message.getBytes(this.encodeType));
            os.flush();
            BufferedReader e = new BufferedReader(new InputStreamReader(client.getInputStream(), this.encodeType));
            e.read(data);
        } catch (SocketException var34) {
            var34.printStackTrace();
        } catch (IOException var35) {
            var35.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    ((BufferedReader)bufferedReader).close();
                } catch (IOException var33) {
                    var33.printStackTrace();
                }
            }

            if(is != null) {
                try {
                    is.close();
                } catch (IOException var32) {
                    ;
                }
            }

            if(os != null) {
                try {
                    os.close();
                } catch (IOException var31) {
                    logger.error(var31.getMessage());
                }
            }

            if(client != null) {
                try {
                    client.close();
                } catch (IOException var30) {
                    logger.error(var30.getMessage());
                }
            }

        }

        return (new String(data)).trim();
    }

    public byte[] sendMessage(byte[] bb, int byteSize) {
        Socket client = null;
        BufferedOutputStream os = null;
        BufferedInputStream is = null;
        Object bufferedReader = null;
        byte[] data = new byte[byteSize];
        byte[] response = null;

        try {
            client = new Socket();
            client.connect(new InetSocketAddress(InetAddress.getByName(this.address), Integer.parseInt(this.port)), this.timeOut);
            client.setSoTimeout(this.timeOut);
            os = new BufferedOutputStream(client.getOutputStream());
            is = new BufferedInputStream(client.getInputStream());
            os.write(bb);
            os.flush();
            int e = client.getInputStream().read(data);
            response = new byte[e];
            System.arraycopy(data, 0, response, 0, e);
        } catch (SocketException var36) {
            var36.printStackTrace();
        } catch (IOException var37) {
            var37.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    ((BufferedReader)bufferedReader).close();
                } catch (IOException var35) {
                    var35.printStackTrace();
                }
            }

            if(is != null) {
                try {
                    is.close();
                } catch (IOException var34) {
                    ;
                }
            }

            if(os != null) {
                try {
                    os.close();
                } catch (IOException var33) {
                    logger.error(var33.getMessage());
                }
            }

            if(client != null) {
                try {
                    client.close();
                } catch (IOException var32) {
                    logger.error(var32.getMessage());
                }
            }

        }

        return response;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }
}
