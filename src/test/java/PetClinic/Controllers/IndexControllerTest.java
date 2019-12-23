package PetClinic.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest
{
    @InjectMocks
    IndexController indexController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(viewResolver).build();
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void unknownUrl() throws Exception
    {
        mockMvc.perform(get("/anyUnknownUrl"))
                .andExpect(status().is4xxClientError());
    }
}