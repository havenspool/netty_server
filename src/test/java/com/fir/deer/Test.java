package com.fir.deer;

import org.json.JSONArray;

/**
 * Created by havens on 16-5-23.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        JSONArray roleList = new JSONArray();
        roleList.put("1");
        roleList.put("2");
        System.out.println(roleList.toString());
    }
}
