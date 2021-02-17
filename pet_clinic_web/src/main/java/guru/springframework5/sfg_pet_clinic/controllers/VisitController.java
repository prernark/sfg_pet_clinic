package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.model.Visit;
import guru.springframework5.sfg_pet_clinic.services.PetService;
import guru.springframework5.sfg_pet_clinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {
    private static final String VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("pet") //called before each @RequestMapping annotated method
    public Pet findPet(@PathVariable Long petId){
        return petService.findById(petId);
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    // Spring MVC calls method findPet(...) before openAddNewPetVisitForm is called
    @GetMapping("visits/new")
    public String openAddNewPetVisitForm(Pet pet, Model model){
        Visit visit = new Visit();
        pet.getVisitSet().add(visit);
        visit.setPet(pet);
        model.addAttribute("visit", visit );
        return VISITS_CREATE_OR_UPDATE_FORM;
    }

    // Spring MVC calls method findPet(...) before processAddNewPetVisitForm is called
    @PostMapping("visits/new")
    public String processAddNewPetVisitForm(Pet pet, @Valid Visit visit, @PathVariable Long ownerId, Model model, BindingResult result){
        pet.getVisitSet().add(visit);
        visit.setPet(pet);

        if (!StringUtils.hasLength(visit.getDescription()) && visit.getDate() == null ) {
            result.rejectValue("description", "empty", "Enter Visit details");
        }
        if (result.hasErrors()) {
            model.addAttribute("visit", visit );
            return VISITS_CREATE_OR_UPDATE_FORM;
        }
        Visit savedVisit = visitService.save(visit);
        return "redirect:/owners/"+ownerId; //could say savedVisit.getPet().getOwner().getId() or pet.getOwner().getId() but testing it is tedious hence simply say ownerId.
    }
}

//Their version....
//    /**
//     * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
//     * we always have fresh data - Since we do not use the session scope, make sure that
//     * Pet object always has an id (Even though id is not part of the form fields)
//     * @param petId
//     * @return Pet
//     */
//    @ModelAttribute("visit")
//    public Visit loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model) {
//        Pet pet = petService.findById(petId);
//        model.put("pet", pet);
//        Visit visit = new Visit();
//        pet.getVisits().add(visit);
//        visit.setPet(pet);
//        return visit;
//    }
//
//    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
//    @GetMapping("/owners/*/pets/{petId}/visits/new")
//    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
//        return "pets/createOrUpdateVisitForm";
//    }
//
//    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
//    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
//    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
//        if (result.hasErrors()) {
//            return "pets/createOrUpdateVisitForm";
//        }
//        else {
//            this.visits.save(visit);
//            return "redirect:/owners/{ownerId}";
//        }
//    }
