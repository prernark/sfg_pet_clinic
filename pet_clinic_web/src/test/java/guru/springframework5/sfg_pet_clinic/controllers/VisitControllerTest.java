package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.services.PetService;
import guru.springframework5.sfg_pet_clinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {
    @Mock
    private VisitService visitService;
    @Mock
    private PetService petService;
    @InjectMocks
    private VisitController visitController;

    MockMvc mockMvc;

    private Pet pet;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        pet = Pet.builder().id(1L).build();
    }

    @Test
    public void openAddNewPetVisitForm() throws Exception {
        //since we've added @ModelAttribute for owner and pet we need to mock those
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("pets/createOrUpdateVisitForm"))
               .andExpect(model().attributeExists("visit"));
    }

    @Test
    public void processAddNewPetVisitFormWithErrors() throws Exception {
        //since we've added @ModelAttribute for owner and pet we need to mock those
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("visit"));
        verifyNoInteractions(visitService);
    }

    @Test
    public void processAddNewPetVisitFormSuccess() throws Exception {
        //since we've added @ModelAttribute for owner and pet we need to mock those
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                        .param("description", "tummyache"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("visit"));
        verify(visitService,times(1)).save(any());
    }
}
