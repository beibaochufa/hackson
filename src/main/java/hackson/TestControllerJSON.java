package hackson;

import hackson.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by whh on 2018/9/13.
 * 不可以实现
 */

@Controller
public class TestControllerJSON {

    private UserInfo userInfo;

    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public UserInfo testSpring() {
        return new UserInfo("whh", "1");
    }
}