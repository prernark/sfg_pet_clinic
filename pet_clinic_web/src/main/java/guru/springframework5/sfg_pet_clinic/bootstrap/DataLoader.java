package guru.springframework5.sfg_pet_clinic.bootstrap;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.model.Vet;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import guru.springframework5.sfg_pet_clinic.services.VetService;
import guru.springframework5.sfg_pet_clinic.services.map.OwnerServiceMapImpl;
import guru.springframework5.sfg_pet_clinic.services.map.VetServiceMapImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMapImpl();
        vetService = new VetServiceMapImpl();

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading owners....");
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Prerna");
        owner.setLastName("Gupta");
        ownerService.save(owner);

        owner = new Owner();
        owner.setId(2L);
        owner.setFirstName("Rahul");
        owner.setLastName("Khole");
        ownerService.save(owner);
        System.out.println("Owners loaded....");
        System.out.println(ownerService.toString());

        System.out.println("Loading vets....");
        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Ananya");
        vet.setLastName("Khole");
        vetService.save(vet);

        vet = new Vet();
        vet.setId(2L);
        vet.setFirstName("Anay");
        vet.setLastName("Khole");
        vetService.save(vet);
        System.out.println("Vets loaded....");
        System.out.println(vetService.toString());
    }
}
