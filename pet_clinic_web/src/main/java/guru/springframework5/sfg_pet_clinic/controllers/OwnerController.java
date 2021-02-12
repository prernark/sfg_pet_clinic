package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners") //Instead of typing owner down several times we can say it once here at Class level
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

//    //    @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
//    @RequestMapping({"", "/", "/index", "/index.html"}) //Dont need above coz of RequestMapping above
//    public String listOfOwners(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//        return "Owners/index";//This string corresponds to index.html in the Owner dir of templates
//    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";//This string corresponds to notimplemented.html in templates
    }

    @GetMapping({"", "/"}) //when Find on above page is clicked, th:action="@{/owners}" hence comes here
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        if (results.isEmpty()) { // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (results.size() == 1) { // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else { // multiple owners found
            model.addAttribute("owners", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/find/{lastName}") //This will work for /owners/find
    public String foundOwners(@PathVariable String lastName, Model model) {
        model.addAttribute("owners", ownerService.findAllByLastNameLike(lastName));
        return "owners/ownersList";//This string corresponds to notimplemented.html in templates
    }

    @GetMapping("/{ownerId}")
    public ModelAndView displayOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }


}
