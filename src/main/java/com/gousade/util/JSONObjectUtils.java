package com.gousade.util;

import com.alibaba.fastjson.JSONObject;
import com.gousade.entity.dto.CqHttpEvent;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JSONObjectUtils {
    public static JSONObject getJSONObject(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

            // 数据写入StringBuilder
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }

    public static CqHttpEvent getCqHttpEvent(HttpServletRequest request) {
        CqHttpEvent result = new CqHttpEvent();
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

            // 数据写入StringBuilder
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            result = JSONObject.parseObject(sb.toString(), CqHttpEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
