package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetsController {
    private final VetService vetService;

    public VetsController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html"})
    public String listOfVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "Vets/index"; //This string corresponds to index.html in the Vets dir of templates
    }
}
