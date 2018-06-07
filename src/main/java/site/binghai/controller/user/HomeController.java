package site.binghai.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String goHome(){
        return "redirect:http://schoolnote.bigdata8.xin/schoolnote/html/case/case.html";
    }

}
