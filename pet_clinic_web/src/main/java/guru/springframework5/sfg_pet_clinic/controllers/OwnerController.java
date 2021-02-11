package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners") //Instead of typing owner down several times we can say it once here at Class level
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //    @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
    @RequestMapping({"", "/", "/index", "/index.html"}) //Dont need above coz of RequestMapping above
    public String listOfOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "Owners/index";//This string corresponds to index.html in the Owner dir of templates
    }

//    @RequestMapping("/find") //This will work for /owners/find
//    public String findOwners(Model model) {
////        model.addAttribute("owners", ownerService.findAll());
//        return "notimplemented";//This string corresponds to notimplemented.html in templates
//    }

    @GetMapping("/{ownerId}")
    public ModelAndView findOwners(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
