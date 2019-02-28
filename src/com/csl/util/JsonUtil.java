package com.csl.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

/**
 * @author 陈思龙
 * @date 2018/12/5 17:09
 * JsonUtil工具类主要将ResultSet数据转换成Json
 * easyui好读取数据
 */
public class JsonUtil {
    public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int num = md.getColumnCount();//获取长度
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <= num; i++) {
                Object o = rs.getObject(i);//返回对象
                if (o instanceof Date) {//如果是时间对象
                    mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((Date) o, "yyyy-MM-dd"));
                } else {
                    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
                }

            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
