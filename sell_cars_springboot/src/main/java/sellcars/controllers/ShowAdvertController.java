package sellcars.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShowAdvertController {
    @RequestMapping(value = "/show_advert", method = RequestMethod.POST)
    public String showAdvert() {
        return "show_advert";
    }
}

