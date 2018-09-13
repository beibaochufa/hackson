package hackson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by whh on 2018/9/13.
 */

@Controller
public class TestController {

    @RequestMapping(value = "/admin")
    @ResponseBody
    public String testSpring() {
        return "My testSpring";
    }
}