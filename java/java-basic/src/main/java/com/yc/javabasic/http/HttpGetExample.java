package com.yc.javabasic.http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpGetExample {
    private static List<String> initData() {
        List<String> dataList = new ArrayList<String>();

        return dataList;
    }


    public static void main(String[] args) throws IOException {
        String baseUrl = "https://members.ruijiandata.com/leasingApi/propertypoi/refreshByPropertyID?propertyId=";
        List<String> dataList = initData();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < dataList.size(); i++) {
            String id = dataList.get(i);
            testDownload(i, baseUrl + id);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时：" + (endTime - startTime) + "ms");
    }

    private static void testDownload(int i, String url) {
            System.out.println("url：" + url);
            long startTime = System.currentTimeMillis();
            URL obj = null;
            try {
                obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // 设置请求方法为GET
            con.setRequestMethod("GET");

            // 打印HTTP响应状态码
            int responseCode = con.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            // 读取HTTP响应数据
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // 打印HTTP响应数据
            System.out.println(response.toString());
            long endTime = System.currentTimeMillis();
            System.out.println(i + "次，耗时：" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
