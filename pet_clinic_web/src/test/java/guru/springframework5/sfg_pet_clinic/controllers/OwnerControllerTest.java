package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownerSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                  .standaloneSetup(ownerController)
                  .build();
    }

    @Test
    void listOfOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.post("/owners"))
               .andExpect(status().is(200)) //OR status().isOk()
               .andExpect(view().name("Owners/index"))
               .andExpect(model().attribute("owners", hasSize(2))) ;

    }

    @Test
    void listOfOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.post("/owners/index"))
                .andExpect(status().is(200)) //OR status().isOk()
                .andExpect(view().name("Owners/index"))
                .andExpect(model().attribute("owners", hasSize(2))) ;

    }

//    @Test
//    void findOwners() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/owners/find"))
//               .andExpect(status().isOk())
//               .andExpect(view().name("notimplemented"));
//        verifyNoInteractions(ownerService);
//    }

    @Test
    public void findOwners() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownerDetails"))
               .andExpect(model().attributeExists("owner"))
               .andExpect(model().attribute("owner", hasProperty("id", is(1L)))) ;
    }
}