package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners") //Instead of typing owner down several times we can say it once here at Class level
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //    @RequestMapping({"/owner", "/owner/index", "/owner/index.html"})
    @RequestMapping({"", "/", "/index", "/index.html"}) //Dont need above coz of RequestMapping above
    public String listOfOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "Owners/index";//This string corresponds to index.html in the Owner dir of templates
    }

    @RequestMapping("/find") //This will work for /owner/find
    public String findOwners(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
        return "notimplemented";//This string corresponds to notimplemented.html in templates
    }
}
