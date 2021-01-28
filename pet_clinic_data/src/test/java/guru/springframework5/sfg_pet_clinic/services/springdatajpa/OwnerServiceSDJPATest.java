package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceSDJPATest {
    public static final String LAST_NAME = "Smith";
    long id = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks //injects repository as a dependency to service. Constructor not needed
    OwnerServiceSDJPA ownerServiceJPA;

    Owner owner;

    @BeforeEach
    void setUp() {
//        ownerServiceJPA = new OwnerServiceSDJPA(ownerRepository); //NOT NEEDED AS @InjectMocks
        owner = Owner.builder().id(id).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> ownerSet = ownerServiceJPA.findAll();
        assertNotNull(ownerSet);
        assertEquals(2,ownerSet.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(owner));
        Owner ownerFound = ownerServiceJPA.findById(id);
        assertNotNull(ownerFound);
        assertEquals(id, ownerFound.getId());
        assertEquals(LAST_NAME, ownerFound.getLastName());
    }

    @Test
    //Returns null if not found
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner ownerFound = ownerServiceJPA.findById(id);
        assertNull(ownerFound);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(id).build();
        when(ownerRepository.save(any())).thenReturn(ownerToSave);
        Owner ownerSaved = ownerServiceJPA.save(ownerToSave);
        assertNotNull(ownerSaved);
        assertEquals(id, ownerSaved.getId());
        assertNull(ownerSaved.getLastName());
        verify(ownerRepository).save(any());
    }

    @Test
    void deleteById() {
        ownerServiceJPA.deleteById(id);
        assertNull(ownerServiceJPA.findById(id));
        //verify by default 1 times
        verify(ownerRepository).deleteById(anyLong()); //to ensure Mock interaction did take place
    }

    @Test
    void delete() {
        ownerServiceJPA.delete(owner);
        assertNull(ownerServiceJPA.findById(id));
        //default is 1 times so we dont really need to write it.
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner ownerFound = ownerServiceJPA.findByLastName(LAST_NAME);
        assertNotNull(ownerFound);
        assertEquals(LAST_NAME, ownerFound.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}