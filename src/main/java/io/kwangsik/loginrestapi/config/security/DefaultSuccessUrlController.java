package io.kwangsik.loginrestapi.config.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings({"all"})
public class DefaultSuccessUrlController {
    @GetMapping("/")
    public ModelAndView initialize() {
        return new ModelAndView("index.html");
    }
}