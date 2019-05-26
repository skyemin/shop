package com.wei.ai.utils;

import okhttp3.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * @Author: weizz
 * @Date: 2019/5/24 14:42
 * @Description:
 * @Version:1.0
 */
public class AiRequestBean {

    public static final String ERROR = "error";
    public static final String APP_ID = "2116841982";
    public static final String APP_KEY = "z5ZsCrddvkRNK1cs";

    private HashMap<String, Object> mParams = new HashMap<>();

    private AiRequestBean() throws IOException {
        //时间戳
        String time_stamp = System.currentTimeMillis() / 1000 + "";
        //随机字符串
        String nonce_str = getRandomString(10);
        //将通用参数设置进map中
        mParams.put("app_id", APP_ID);
        mParams.put("nonce_str", nonce_str);
        mParams.put("time_stamp", time_stamp);
        byte[] imageData = getBytes("d://3.png");
        //准备好图片base64数据
        String img64 = Base64Util.encode(imageData);
        mParams.put("image",img64);
        String sign = TencentAISignHolder.getSignature(mParams,APP_KEY);
        mParams.put("sign",sign);
    }

    //发起请求
    public String request(String url) throws IOException {
        //使用okhttp发起请求
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Iterator<String> iterator = mParams.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = (String) mParams.get(key);
            builder.addFormDataPart(key, value);
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().header("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()){
            return ERROR;
        }
        return response.body().string();
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void main(String[] args) throws IOException {
        AiRequestBean aiRequestBean = new AiRequestBean();
        String jsonResult = aiRequestBean.request("https://api.ai.qq.com/fcgi-bin/vision/vision_porn");
        System.out.println(jsonResult);
        /*JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = JSONObject.parseObject(data);
        JSONArray array = jsonObject1.getJSONArray("tag_list");
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject2 = array.getJSONObject(i);
            System.out.println(jsonObject2.getString("tag_name"));
        }*/
    }
}
