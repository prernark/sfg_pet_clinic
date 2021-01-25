package guru.springframework5.sfg_pet_clinic.bootstrap;

import guru.springframework5.sfg_pet_clinic.model.*;
import guru.springframework5.sfg_pet_clinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
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

        System.out.println("Loading Speciality....");
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);
        System.out.println("Speciality loaded....");
        System.out.println(savedRadiology.getDescription()+" "+savedSurgery.getDescription()+" "+savedDentistry.getDescription());

        System.out.println("Loading owners....");
        Owner owner = new Owner();
//        owner.setId(1L);
        owner.setFirstName("Prerna");
        owner.setLastName("Gupta");
        owner.setAddress("123 Sutton Lane");
        owner.setCity("London");
        owner.setTelephone("123456");

        Pet myPet = new Pet();
        myPet.setName("Nina");
        myPet.setPetType(savedDogPetType);
        myPet.setOwner(owner);
        myPet.setBirthDate(LocalDate.now());
        owner.getPetSet().add(myPet);
        ownerService.save(owner);
        System.out.println(owner.getId()+" "+owner.getFirstName()+" "+
                owner.getLastName()+" "+owner.getAddress()+" "+owner.getCity()+
                owner.getTelephone()+" "+owner.getPetSet().iterator().next().getName());
        //Add a visit for the pet
        Visit myVisit = new Visit();
        myVisit.setDate(LocalDate.now());
        myVisit.setDescription("Not Eating well");
        myVisit.setPet(myPet);
        visitService.save(myVisit);
        System.out.println("Loaded Visits - "+myVisit.getDescription()+" "+myVisit.getDate()+" "+myVisit.getPet().getName());

        owner = new Owner();
//        owner.setId(2L);
        owner.setFirstName("Rahul");
        owner.setLastName("Khole");
        owner.setAddress("789 MG Road");
        owner.setCity("Pune");
        owner.setTelephone("987654");

        myPet = new Pet();
        myPet.setName("Butch");
        myPet.setPetType(savedCatPetType);
        myPet.setOwner(owner);
        myPet.setBirthDate(LocalDate.now());
        owner.getPetSet().add(myPet);

        ownerService.save(owner);
        System.out.println(owner.getId()+" "+owner.getFirstName()+" "+
                owner.getLastName()+" "+owner.getAddress()+" "+owner.getCity()+
                owner.getTelephone()+" "+owner.getPetSet().iterator().next().getPetType().getName());

        System.out.println("Owners loaded....");
        System.out.println(ownerService.toString());

        System.out.println("Loading vets....");
        Vet vet = new Vet();
//        vet.setId(1L);
        vet.setFirstName("Ananya");
        vet.setLastName("Khole");
        vet.getSpecialitySet().add(savedRadiology);
        vet.getSpecialitySet().add(savedSurgery);
        vet.getSpecialitySet().add(savedDentistry);
        vetService.save(vet);
        System.out.println("Vet Speciality ");
        vet.getSpecialitySet().forEach(speciality -> System.out.println(speciality.getDescription()+" "));

        vet = new Vet();
//        vet.setId(2L);
        vet.setFirstName("Anay");
        vet.setLastName("Khole");
        vet.getSpecialitySet().add(savedRadiology);
        vetService.save(vet);
        System.out.println("Vet Speciality ");
        vet.getSpecialitySet().forEach(speciality -> System.out.println(speciality.getDescription()+" "));
        System.out.println("Vets loaded....");
        System.out.println(vetService.toString());
    }
}
