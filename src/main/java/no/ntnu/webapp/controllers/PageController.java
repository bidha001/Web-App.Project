package no.ntnu.webapp.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

  @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/informationTechnologies")
    public String informationTechnologies() {
        return "informationTechnologies";
    }

    @GetMapping("/digitalMarketing")
    public String digitalMarketing() {
        return "digitalMarketing";
    }

    @GetMapping("/dataScienceAnalytics")
    public String dataScienceAnalytics() {
        return "dataScienceAnalytics";
    }

    @GetMapping("/businessEntrepreneurship")
    public String businessEntrepreneurship() {
        return "businessEntrepreneurship";
    }

}
