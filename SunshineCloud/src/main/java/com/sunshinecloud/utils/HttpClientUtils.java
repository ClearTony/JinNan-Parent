package com.sunshinecloud.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpClientUtils {
    private final static String publicKey = "D71CDE83E6833A9F3D30C54E597D6AC1";// 阳光云分配的publicKey
    private final static String x_access_key = "xd2i8rg9nt28cb6gbnwh6vxcx2n05cqe";//阳光云分配的accessKey
    private final static String ApiUrl = "https://gateway.isolarcloud.com";

    /**
     * 发送POST请求
     *
     * @param url 请求的URL地址
     * @param req 要发送的JSON字符串
     * @return 响应内容
     * @throws Exception 如果请求失败或发生异常
     */
    public static String sendPost(String url, Map<String, Object> req) throws Exception {
        // 创建HttpClient实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpPost对象
            HttpPost httpPost = new HttpPost(ApiUrl + url);
            // 设置请求头
            httpPost.addHeader("sys_code", "901");
            httpPost.addHeader("x-access-key", x_access_key);
            // 公共参数
            req.put("appkey", publicKey);
            String jsonStr = JSON.toJSONString(req);
            StringEntity strEntity = new StringEntity(jsonStr);
            strEntity.setContentType("application/json");
            httpPost.setEntity(strEntity);
            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream, "UTF-8");
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder result = new StringBuilder();
                String s;
                while (((s = reader.readLine()) != null)) {
                    result.append(s);
                }
                reader.close();
                System.out.println("receive json-<" + result);
                return result.toString();
            }
        } catch (Exception e) {
            throw new Exception("Error executing POST request", e);
        }
    }
}