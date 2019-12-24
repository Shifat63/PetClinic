package PetClinic.Controllers;

import PetClinic.Model.Speciality;
import PetClinic.Model.Vet;
import PetClinic.Service.SpecialityService;
import PetClinic.Service.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    VetService vetService;
    @Mock
    SpecialityService specialityService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;
    Long vetId = 1L;
    Long specialityId = 1L;
    Vet vet = new Vet();
    Speciality speciality = new Speciality();
    Set<Vet> vets = new HashSet<>();
    Set<Speciality> specialities = new HashSet<>();

    @BeforeEach
    void setUp() {
        vet.setId(vetId);
        vets.add(vet);
        speciality.setId(specialityId);
        specialities.add(speciality);
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void index() throws Exception {
        when(vetService.findAll()).thenReturn(vets);
        when(specialityService.findAll()).thenReturn(specialities);

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("vets", hasSize(1)))
                .andExpect(model().attribute("specialities", hasSize(1)))
                .andExpect(view().name("vets/index"));

        mockMvc.perform(get("/vets/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("vets", hasSize(1)))
                .andExpect(model().attribute("specialities", hasSize(1)))
                .andExpect(view().name("vets/index"));

        mockMvc.perform(get("/vets/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("vets", hasSize(1)))
                .andExpect(model().attribute("specialities", hasSize(1)))
                .andExpect(view().name("vets/index"));

        verify(vetService, times(3)).findAll();
        verify(specialityService, times(3)).findAll();
    }

    @Test
    void searchVets() throws Exception {
        when(vetService.findByCriteria(any(), any())).thenReturn(vets);
        when(specialityService.findAll()).thenReturn(specialities);

        mockMvc.perform(post("/vets/searchVets")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "mock name")
                .param("speciality", "")
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("vets", hasSize(1)))
                .andExpect(model().attribute("specialities", hasSize(1)))
                .andExpect(view().name("vets/index"));

        verify(vetService, times(1)).findByCriteria(any(), any());
        verify(specialityService, times(1)).findAll();
    }
}