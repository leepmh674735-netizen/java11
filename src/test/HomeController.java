ppackage com.winter.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}ackage


@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String homw() {
        return "home";
    }
}