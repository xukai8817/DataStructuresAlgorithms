package edu.other.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author xukai<br>
 * if (condition1 || condition2)<br>
 * condition1 = true, condition2 don't be checked
 */
public class ORTest {
	public static void main(String[] args) {
		
		String str1 = "1";
		String str2 = "2";
		if ((str1 == "1") || (str2="3") == "4") 
			System.out.println(str2);
		System.out.println(str1);
		System.out.println(str2);
		String content = "{\"data\":\"a" +String.valueOf((char)160) + "\nbc\"}";
		JSONObject jsonObject = JSON.parseObject(content);
		System.out.println(jsonObject);
	}
}
