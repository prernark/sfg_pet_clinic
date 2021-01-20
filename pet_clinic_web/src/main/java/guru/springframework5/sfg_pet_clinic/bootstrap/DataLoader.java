package guru.springframework5.sfg_pet_clinic.bootstrap;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.model.Vet;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import guru.springframework5.sfg_pet_clinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading owners....");
        Owner owner = new Owner();
//        owner.setId(1L);
        owner.setFirstName("Prerna");
        owner.setLastName("Gupta");
        ownerService.save(owner);

        owner = new Owner();
//        owner.setId(2L);
        owner.setFirstName("Rahul");
        owner.setLastName("Khole");
        ownerService.save(owner);
        System.out.println("Owners loaded....");
        System.out.println(ownerService.toString());

        System.out.println("Loading vets....");
        Vet vet = new Vet();
//        vet.setId(1L);
        vet.setFirstName("Ananya");
        vet.setLastName("Khole");
        vetService.save(vet);

        vet = new Vet();
//        vet.setId(2L);
        vet.setFirstName("Anay");
        vet.setLastName("Khole");
        vetService.save(vet);
        System.out.println("Vets loaded....");
        System.out.println(vetService.toString());

        System.out.println("Loading PetTypes....");
        PetType dog = new PetType();
        dog.setName("Dog");
        //NOW I WANT TO USE THIS PETTYPE lateron HENCE SAVE IT
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);
        System.out.println("PetTypes loaded....");
        System.out.println(petTypeService.toString());
    }
}
