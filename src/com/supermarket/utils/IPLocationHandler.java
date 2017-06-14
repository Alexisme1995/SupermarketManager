package com.supermarket.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 */
public class IPLocationHandler {
    private static String readAll(Reader rd)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 解析Json格式数据
     *
     * @param url URL地址
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url)
            throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /**
     * 获取本机IP
     *
     * @return 返回IP地址
     * @throws IOException
     * @throws JSONException
     */
    public static String getIp()
            throws IOException, JSONException {
        String url = "http://pv.sohu.com/cityjson?ie=utf-8";
        InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(rd).substring(19);
        jsonText = jsonText.substring(0, jsonText.length() - 1);
        JSONObject json = new JSONObject(jsonText);
        return json.getString("cip");
    }

    /**
     * 获取IP地址对应地理位置
     *
     * @return
     */
    public static String getIPLocation(String IP) {
        if (IP.equals("0:0:0:0:0:0:0:1")) {
            return "本地操作";
        }
        String result = "";
        try {
            JSONObject rootJson = readJsonFromUrl("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);
            JSONObject dataJson = (JSONObject) rootJson.get("data");
            //省份
            String province = dataJson.get("region").toString();
            //城市
            String city = dataJson.get("city").toString();
            //运营商
            String isp = dataJson.get("isp").toString();
            result = IP + " (" + province + city + " " + isp + ")";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args)
            throws IOException, JSONException {
//        JSONObject json = readJsonFromUrl("http://ip.taobao.com/service/getIpInfo.php?ip=127.0.0.1");
//        System.out.println(json.toString());
//        System.out.println(((JSONObject) json.get("data")).get("isp"));
        System.out.println(getIPLocation("211.143.16.122"));
    }
}
