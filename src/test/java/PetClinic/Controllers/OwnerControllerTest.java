package PetClinic.Controllers;

import PetClinic.Model.Owner;
import PetClinic.Service.OwnerService;
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
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;
    Owner owner;
    MockMvc mockMvc;

    String overSizedString = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxwwyyzz" +
                            "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxwwyyzz" +
                            "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxwwyyzz" +
                            "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxwwyyzz" +
                            "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxwwyyzz";

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owner = new Owner();
        owner.setId(1L);
        owner.setLastName("Haque");
        owners.add(owner);
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setLastName("Khan");
        owners.add(owner2);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void index() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        verify(ownerService, times(3)).findAll();
    }

    @Test
    void registerOwnerGet() throws Exception {
        mockMvc.perform(get("/owners/registerOwner"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/registerOwner"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void registerOwnerPost() throws Exception {
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/viewOwnerDetails/1"));

        verify(ownerService, times(1)).save(any());
    }

    @Test
    void registerOwnerPostFirstNameValidationFail() throws Exception {

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "") //firstName can't be empty. So validation failed
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", overSizedString) //firstName must be between 1 to 25 characters. So validation failed
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        verify(ownerService, times(0)).save(any());
    }

    @Test
    void registerOwnerPostLastNameValidationFail() throws Exception {

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "") //lastName can't be empty. So validation failed
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", overSizedString) //lastName must be between 1 to 25 characters. So validation failed
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        verify(ownerService, times(0)).save(any());
    }

    @Test
    void registerOwnerPostAddressValidationFail() throws Exception {

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "") //address can't be empty. So validation failed
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", overSizedString) //address must be between 1 to 255 characters. So validation failed
                .param("city", "mock city")
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        verify(ownerService, times(0)).save(any());
    }

    @Test
    void registerOwnerPostCityValidationFail() throws Exception {

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "") //city can't be empty. So validation failed
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", overSizedString) //city must be between 1 to 255 characters. So validation failed
                .param("telephone", "123456")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        verify(ownerService, times(0)).save(any());
    }

    @Test
    void registerOwnerPostTelephoneValidationFail() throws Exception {

        String overSizedTelephone = "1234567890098765432112345";
        String underSizedTelephone = "1234";

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "") //telephone can't be empty. So validation failed
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", underSizedTelephone) //telephone must be greater than or equal to 5 characters. So validation failed
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", overSizedTelephone) //telephone must be less than or equal to 20 characters. So validation failed
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        mockMvc.perform(post("/owners/registerOwner")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "mock first name")
                .param("lastName", "mock last name")
                .param("address", "mock address")
                .param("city", "mock city")
                .param("telephone", "abc") //telephone must be number. So validation failed
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        verify(ownerService, times(0)).save(any());
    }

    @Test
    void viewOwnerDetails() throws Exception {
        when(ownerService.findById(any())).thenReturn(owner);

        mockMvc.perform(get("/owners/viewOwnerDetails/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/viewOwnerDetails"));

        verify(ownerService, times(1)).findById(any());
    }

    @Test
    void editOwner() throws Exception {
        when(ownerService.findById(any())).thenReturn(owner);

        mockMvc.perform(get("/owners/editOwner/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/registerOwner"));

        verify(ownerService, times(1)).findById(any());
    }

    @Test
    void deleteOwner() throws Exception {
        mockMvc.perform(get("/owners/deleteOwner/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/index"));

        verify(ownerService, times(1)).deleteById(any());
    }
}