package guru.springframework5.sfg_pet_clinic.controllers;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

//    @Test
//    void listOfOwners() throws Exception {
//        when(ownerService.findAll()).thenReturn(ownerSet);
//        mockMvc.perform(MockMvcRequestBuilders.post("/owners"))
//               .andExpect(status().is(200)) //OR status().isOk()
//               .andExpect(view().name("Owners/index"))
//               .andExpect(model().attribute("owners", hasSize(2))) ;
//
//    }

//    @Test
//    void listOfOwnersByIndex() throws Exception {
//        when(ownerService.findAll()).thenReturn(ownerSet);
//        mockMvc.perform(MockMvcRequestBuilders.post("/owners/index"))
//                .andExpect(status().is(200)) //OR status().isOk()
//                .andExpect(view().name("Owners/index"))
//                .andExpect(model().attribute("owners", hasSize(2))) ;
//
//    }

    @Test
    public void displayOwner() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L)))) ;
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/findOwners"))
               .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    public void processFindOwnerNoLastname() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(ownerSet.stream().collect(Collectors.toList()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(ownerService, times(1)).findAllByLastNameLike(argumentCaptor.capture());
        String lastName = argumentCaptor.getValue();
        assertEquals("%%", lastName);
    }

    @Test
    public void processFindOwnerReturnsMany() throws Exception{
        when(ownerService.findAllByLastNameLike(anyString()))
                         .thenReturn(Arrays.asList(Owner.builder().id(1L).build(),Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownersList"))
               .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    public void processFindOwnerReturnsOne() throws Exception{
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    public void openCreateOwnerForm() throws Exception{
        mockMvc.perform(get("/owners/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/createOrUpdateOwnerForm"))
               .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    public void processCreateOwnerForm() throws Exception{
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/new"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"))
               .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).save(any());
    }

    @Test
    public void openUpdateOwnerForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        mockMvc.perform(get("/owners/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    public void processUpdateOwnerForm() throws Exception{
//        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).save(any());
    }

}