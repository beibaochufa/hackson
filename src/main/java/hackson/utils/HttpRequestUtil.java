package hackson.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by whh on 2018/9/14.
 */
public class HttpRequestUtil {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url     发送请求的URL
     * @param map     请求Map参数，请求参数应该是 {"name1":"value1","name2":"value2"}的形式。
     * @param charset 发送和接收的格式
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, Object> map, String charset) {
        StringBuffer sb = new StringBuffer();
        //构建请求参数
        if (map != null && map.size() > 0) {
            Iterator it = map.entrySet().iterator(); //定义迭代器
            int i = 0;
            while (it.hasNext()) {
                if (i > 0){
                    sb.append("&");
                }
                Map.Entry er = (Map.Entry) it.next();
                sb.append(er.getKey());
                sb.append("=");
                sb.append(er.getValue());
                i++;
            }
        }
        return sendGet(url, sb.toString(), charset);
    }


    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url     发送请求的URL
     * @param map     请求Map参数，请求参数应该是 {"name1":"value1","name2":"value2"}的形式。
     * @param charset 发送和接收的格式
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, Object> map, String charset) {
        StringBuffer sb = new StringBuffer();
        //构建请求参数
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> e : map.entrySet()) {
                // TODO: 2018/9/14

                sb.append(e.getKey());


                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
        }
        return sendPost(url, sb.toString(), charset);
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url     发送请求的URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset 发送和接收的格式
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String charset) {
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println("url = " + urlNameString);
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性 设置请求格式
//            conn.setRequestProperty("contentType", charset);
//            conn.setRequestProperty("content-type", "application/json");
//            //设置超时时间
//            conn.setConnectTimeout(60);
//            conn.setReadTimeout(60);


            HttpURLConnection conn = (HttpURLConnection) new URL(urlNameString).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
//            conn.connect();


            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应,设置接收格式
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String url, String param, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        try {
//            URL realUrl = new URL(url);
            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性 设置请求格式
//            conn.setRequestProperty("contentType", charset);
//            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");

            System.out.println("post-url = " + url);

            //设置超时时间
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
            conn.connect();
            // 发送POST请求必须设置如下两行
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            System.out.println("post-body = " + param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();

        } catch (Exception e) {
            System.out.println("发送 POST请求出现异常!" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
