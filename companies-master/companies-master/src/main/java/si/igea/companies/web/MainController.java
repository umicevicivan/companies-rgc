package si.igea.companies.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
public class MainController {

    @RequestMapping(value = {"index.html", "/"}, method = RequestMethod.GET)
    public String index() {
        return "redirect:/companies/list/1";
    }
}
