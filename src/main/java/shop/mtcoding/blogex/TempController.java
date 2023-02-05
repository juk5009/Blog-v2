package shop.mtcoding.blogex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("/temp")
    public String temp() {
        return "temp";
    }
}