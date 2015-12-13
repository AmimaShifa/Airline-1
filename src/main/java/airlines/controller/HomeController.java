package airlines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by winio_000 on 2015-11-02.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "/web/index";
    }

}
