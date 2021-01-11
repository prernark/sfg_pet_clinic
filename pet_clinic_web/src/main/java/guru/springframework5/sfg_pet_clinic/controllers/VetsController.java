package guru.springframework5.sfg_pet_clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetsController {

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html"})
    public String listOfVets() {
        return "Vets/index"; //This string corresponds to index.html in the Vets dir of templates
    }
}
