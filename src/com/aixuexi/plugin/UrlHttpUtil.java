package com.aixuexi.plugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.aixuexi.plugin.PROD.println;


/**
 * Created by fighting on 2017/4/24.
 */

public class UrlHttpUtil {

    public static void downLoad(String HTTP_URL,String dest){
        int count = 0;
        BufferedInputStream bis =null;
        BufferedOutputStream bos=null;
//        String HTTP_URL="http://f0.topitme.com/0/7a/63/113144393585b637a0o.jpg"; //图片地址
        try {
            int contentLength = getConnection(HTTP_URL).getContentLength();
            println("文件的大小是:"+contentLength/1024/1024f);
            if (contentLength>32) {
                InputStream is= getConnection(HTTP_URL).getInputStream();
                bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream(dest);
                bos= new BufferedOutputStream(fos);
                int b = 0;
                byte[] byArr = new byte[1024];
                while((b= bis.read(byArr))!=-1){
                    bos.write(byArr, 0, b);
//                    if (count%100==0){
//                        System.out.println("下载的文件循环==="+count);
//                    }
//                    count++;
                }
                println("下载的文件的大小是----------------------:"+contentLength/1024/1024);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(bis !=null){
                    bis.close();
                }
                if(bos !=null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static HttpURLConnection getConnection(String httpUrl) throws Exception {
        URL url = new URL(httpUrl);
        HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.connect();
        return connection;

    }

    public static void downloadRes(String HTTP_URL,String dest){
        BufferedInputStream bis =null;
        BufferedOutputStream bos=null;
        try {
            int contentLength = getConnection(HTTP_URL).getContentLength();
            if (contentLength>32) {
                InputStream is= getConnection(HTTP_URL).getInputStream();
                bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream(dest);
                bos= new BufferedOutputStream(fos);
                int b = 0;
                byte[] byArr = new byte[1024];
                while((b= bis.read(byArr))!=-1){
                    bos.write(byArr, 0, b);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(bis !=null){
                    bis.close();
                }
                if(bos !=null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
