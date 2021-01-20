package guru.springframework5.sfg_pet_clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index"; //this will search for index.html in templates
    }

    @RequestMapping("/oups") //This will work for /oups
    public String oupsHandler(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
        return "notimplemented";//This string corresponds to notimplemented.html in templates
    }
}
