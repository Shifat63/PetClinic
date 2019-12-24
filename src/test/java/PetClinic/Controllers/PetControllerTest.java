package PetClinic.Controllers;

import PetClinic.Converters.StringToOwner;
import PetClinic.Converters.StringToPetType;
import PetClinic.Model.Owner;
import PetClinic.Model.Pet;
import PetClinic.Model.PetType;
import PetClinic.Service.OwnerService;
import PetClinic.Service.PetService;
import PetClinic.Service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @Mock
    PetService petService;
    @Mock
    OwnerService ownerService;
    @Mock
    PetTypeService petTypeService;

    StringToOwner stringToOwner = new StringToOwner();
    StringToPetType stringToPetType = new StringToPetType();

    Long petId = 1L;
    Long ownerId = 1L;
    Long petTypeId = 1L;
    Pet pet = new Pet();
    Owner owner = new Owner();
    PetType petType = new PetType();
    Set<Pet> pets = new HashSet<>();
    Set<Owner> owners = new HashSet<>();
    Set<PetType> petTypes = new HashSet<>();
    MockMvc mockMvc;

    @InjectMocks
    PetController petController;

    @BeforeEach
    void setUp() {
        pet.setId(petId);
        pets.add(pet);
        owner.setId(ownerId);
        owners.add(owner);
        petType.setId(petTypeId);
        petTypes.add(petType);

        DefaultFormattingConversionService formattingConversionService = new DefaultFormattingConversionService();
        formattingConversionService.addConverter(stringToOwner);
        formattingConversionService.addConverter(stringToPetType);

        mockMvc = MockMvcBuilders.standaloneSetup(petController).setConversionService(formattingConversionService).build();
    }

    @Test
    void index() throws Exception {
        when(petService.findAll()).thenReturn(pets);

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pets", hasSize(1)))
                .andExpect(view().name("pets/index"));

        mockMvc.perform(get("/pets/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pets", hasSize(1)))
                .andExpect(view().name("pets/index"));

        mockMvc.perform(get("/pets/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pets", hasSize(1)))
                .andExpect(view().name("pets/index"));

        verify(petService, times(3)).findAll();
    }

    @Test
    void viewPetDetails() throws Exception {
        when(petService.findById(any())).thenReturn(pet);

        mockMvc.perform(get("/pets/viewPetDetails/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/viewPetDetails"));

        verify(petService, times(1)).findById(any());
    }

    @Test
    void registerPetGet() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/pets/registerPet"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)))
                .andExpect(view().name("pets/registerPet"));

        verify(ownerService, times(1)).findAll();
        verify(petTypeService, times(1)).findAll();
    }

    @Test
    void registerPetPost() throws Exception {
        when(petService.save(any())).thenReturn(pet);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "mock name")
                .param("birthDate", formatter.format(LocalDate.now()))
                .param("petType", petTypeId.toString())
                .param("owner", ownerId.toString())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pets/viewPetDetails/" + petId));

        verify(petService, times(1)).save(any());
    }

    @Test
    void registerPetPostOwnerValidationFail() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        when(petTypeService.findAll()).thenReturn(petTypes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "mock name")
                .param("birthDate", formatter.format(LocalDate.now()))
                .param("petType", petTypeId.toString())
                .param("owner", "") //There must be no pet without owner. So validation failed
        )
                .andExpect(status().isOk())
                .andExpect(view().name("pets/registerPet"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)));

        verify(petService, times(0)).save(any());
        verify(ownerService, times(1)).findAll();
        verify(petTypeService, times(1)).findAll();
    }

    @Test
    void registerPetPostPetTypeValidationFail() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        when(petTypeService.findAll()).thenReturn(petTypes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "mock name")
                .param("birthDate", formatter.format(LocalDate.now()))
                .param("petType", "") //Every pet must have a pet type. So validation failed
                .param("owner", ownerId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("pets/registerPet"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)));

        verify(petService, times(0)).save(any());
        verify(ownerService, times(1)).findAll();
        verify(petTypeService, times(1)).findAll();
    }

    @Test
    void registerPetPostBirthDateValidationFail() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        when(petTypeService.findAll()).thenReturn(petTypes);

        DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter wrongFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "mock name")
                //birthDate must be present date or any past date. So validation failed
                .param("birthDate", correctFormat.format(LocalDate.now().plus(1, ChronoUnit.DAYS)))
                .param("petType",petTypeId.toString())
                .param("owner", ownerId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("pets/registerPet"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)));

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "mock name")
                //birthDate format mismatch. So validation failed
                .param("birthDate", wrongFormat.format(LocalDate.now()))
                .param("petType",petTypeId.toString())
                .param("owner", ownerId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("pets/registerPet"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)));

        verify(petService, times(0)).save(any());
        verify(ownerService, times(2)).findAll();
        verify(petTypeService, times(2)).findAll();
    }

    @Test
    void registerPetPostNameValidationFail() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        when(petTypeService.findAll()).thenReturn(petTypes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String overSizedName = "abcdefghijklmnopqrstuvwxyz";

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "")//Name must not be empty. So validation failed
                .param("birthDate", formatter.format(LocalDate.now()))
                .param("petType", petTypeId.toString())
                .param("owner", ownerId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("pets/registerPet"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)));

        mockMvc.perform(post("/pets/registerPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", overSizedName)//Name must be between 1 to 20 characters. So validation failed
                .param("birthDate", formatter.format(LocalDate.now()))
                .param("petType", petTypeId.toString())
                .param("owner", ownerId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("pets/registerPet"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)));

        verify(petService, times(0)).save(any());
        verify(ownerService, times(2)).findAll();
        verify(petTypeService, times(2)).findAll();
    }

    @Test
    void editPet() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        when(petService.findById(any())).thenReturn(pet);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/pets/editPet/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("owners", hasSize(1)))
                .andExpect(model().attribute("petTypes", hasSize(1)))
                .andExpect(view().name("pets/registerPet"));

        verify(ownerService, times(1)).findAll();
        verify(petService, times(1)).findById(any());
        verify(petTypeService, times(1)).findAll();
    }

    @Test
    void deletePet() throws Exception {
        mockMvc.perform(get("/pets/deletePet/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pets/index"));

        verify(petService, times(1)).deleteById(any());
    }
}