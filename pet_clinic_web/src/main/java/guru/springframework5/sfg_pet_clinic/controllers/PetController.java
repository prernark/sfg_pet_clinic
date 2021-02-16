package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import guru.springframework5.sfg_pet_clinic.services.PetService;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("owners/{ownerId}")
public class PetController {
    private static final String PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;

    public PetController(PetTypeService petTypeService, OwnerService ownerService, PetService petService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.petService = petService;
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

    @PostMapping("/pets/new")
    public String processAddNewPetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "aleady exists");
        }
        owner.getPetSet().add(pet);
        if (result.hasErrors()){
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_FORM;
        }
        else{
            petService.save(pet);
            return "redirect:/owners/ownerDetails";
        }
    }

    @GetMapping("/pets/{id}/edit")
    public String openUpdatePetForm(@PathVariable Long id, Model model){
        model.addAttribute("pet", petService.findById(id));
        return PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{id}/edit")
    public String processUpdatePetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if (result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_FORM;
        }
        else{
            owner.getPetSet().add(pet);
            petService.save(pet);
            return "redirect:/owners/ownerDetails";
        }
    }


}
