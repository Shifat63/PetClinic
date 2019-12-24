package PetClinic.Controllers;

import PetClinic.Service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlingControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

//    As @Autowired annotation is used, @ExtendWith(SpringExtension.class) needs to be used to generate Spring bean
//    and @WebAppConfiguration needs to be used to configure application.
//    @ExtendWith(MockitoExtension.class) does not create Spring bean
//    Much time will be required to test with @ExtendWith(SpringExtension.class) than @ExtendWith(MockitoExtension.class) as
//    Spring bean needs to be created for @ExtendWith(SpringExtension.class)
//    @Autowired
//    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
//        standaloneSetup setup MockMvc for a specific controller only. So need to set ControllerAdvice as Exception is handeled
//        by a ControllerAdvice
//        webAppContextSetup setup MockMvc for whole Spring application context
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).setControllerAdvice(new ExceptionHandlingController()).build();
    }

    @Test
    void anyException() throws Exception {
        when(ownerService.findById(any())).thenThrow(new Exception());

        mockMvc.perform(get("/owners/viewOwnerDetails/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("exception"))
                .andExpect(view().name("error"));

        verify(ownerService, times(1)).findById(any());
    }
}