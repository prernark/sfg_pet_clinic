package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapImplTest {
    OwnerServiceMapImpl ownerServiceMap;
    Long id = 1L;
    String lastName = "Harris";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMapImpl(new PetTypeServiceMapImpl(), new PetServiceMapImpl());
        ownerServiceMap.save(Owner.builder().id(id).firstName("Andy").lastName(lastName).city("London").build());
    }

    @Test
    void findAll() {
        Set<Owner> ownersFound = ownerServiceMap.findAll();
        assertEquals(1, ownersFound.size());

    }

    @Test
    void findById() {
        assertEquals(id, ownerServiceMap.findById(id).getId());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveWithId() {
        Long id = 2L;
        String lastName = "Mills";

        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(id).firstName("Colin").lastName(lastName).city("Surbiton").build());
        assertEquals(2, ownerServiceMap.findAll().size());
        assertEquals(id, savedOwner.getId());
        assertEquals(lastName, savedOwner.getLastName());
    }

    @Test
    void saveNoId(){
        Owner savedOwner = ownerServiceMap.save(Owner.builder().firstName("Rox").lastName("Ali").build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertEquals("Ali", savedOwner.getLastName());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(id, owner.getId());
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound(){
        Owner owner = ownerServiceMap.findByLastName("hkjh");
        assertNull(owner);
    }
}