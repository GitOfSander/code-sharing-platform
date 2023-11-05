package platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/code")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/code/new")
    public String getNew() {
        return "new";
    }
}
