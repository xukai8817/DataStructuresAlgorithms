package edu.other.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestFastJSON {

    public static void main(String[] args) {
        String content = "{\"data\":\"a\n" + String.valueOf((char) 160) + "\nbc\"}";
        JSONObject jsonObject = JSON.parseObject(content);
        System.out.println(jsonObject);
    }
}
