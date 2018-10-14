package hackson.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hackson.entity.BodyText;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whh on 2018/9/14.
 */
public class WechatUtil {
    // TODO: 2018/9/14 后续写在配置文件中
    private static final String APPID = "wwd160a47aadac9970";
    private static final String AGENTID = "1000002";
    private static final String SCHEMA = "wwauthd160a47aadac9970000002";
    private static final String SECRECT = "u-wyUrn5OX176Giwcely3bT1OvLljLEJgIeqPmnNhSw";

    public static final String URL = "https://qyapi.weixin.qq.com/cgi-bin/";

    public static String getAccessToken() {
        Map<String, Object> param = new HashMap<>();
        param.put("corpid", APPID);
        param.put("corpsecret", SECRECT);
        String rs = HttpRequestUtil.sendGet(URL + "gettoken", param, "UTF-8");

        return JSON.parseObject(rs).getString("access_token");

    }

    public static String getUserId(String accessToken, String code) {
        Map<String, Object> param = new HashMap<>();
        param.put("access_token", accessToken);
        param.put("code", code);
        String response = HttpRequestUtil.sendGet(URL + "user/getuserinfo", param, "utf-8");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(response);
        String userId = jsonObject.getString("UserId");

        return userId;
    }

    //用竖线分割 id
    public static String sendText(String text, String userId) {
        BodyText message = new BodyText(userId, "", "", AGENTID, text);
        String accessToken = getAccessToken();
        return HttpRequestUtil.sendPost(URL + "message/send?access_token=" + accessToken, JSON.toJSONString(message), "utf-8");
    }


}
