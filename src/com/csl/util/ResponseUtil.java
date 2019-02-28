package com.csl.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 陈思龙
 * @date 2018/12/5 17:33
 * 向页面输出
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());//输出
        out.flush();//清空缓存
        out.close();//关闭
    }
}
