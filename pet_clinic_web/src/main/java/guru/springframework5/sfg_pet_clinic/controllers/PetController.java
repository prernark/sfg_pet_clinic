package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("owners/{ownerId}")
public class PetController {
    private static final String PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetTypeService petTypeService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String openAddNewPetForm(Owner owner, Model model){
        Pet pet = new Pet();
        owner.getPetSet().add(pet);
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_FORM;
    }
}
