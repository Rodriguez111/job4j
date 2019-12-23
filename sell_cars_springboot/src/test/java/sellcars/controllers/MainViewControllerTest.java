package sellcars.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Filter;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import sellcars.models.*;
import sellcars.repository.*;
import sellcars.service.ValidateAdvert;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MainViewController.class)
public class MainViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValidateAdvert validateAdvert;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarBrandRepository carBrandRepository;

    @MockBean
    private CarBodyRepository carBodyRepository;

    @MockBean
    private EngineRepository engineRepository;

    @MockBean
    private TransmissionRepository transmissionRepository;


    @Test
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    public void whenAuthorizedAndGetMainViewThenReturnMainPage() throws Exception {
        User user = new User("ivan", "123", "Ivan", "Ivanov", "123", "email");
        Car car = new Car(100, 1999, "vin", "red", new CarBrand("BMW"), "Model1", new CarBody("TestBody"), new Engine("TestEngine"), 2.5, new Transmission("TesrTr"));
        Advert advert = new Advert("10.10.2019", car, user, false);
        List<Advert> list = List.of(advert);
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given(validateAdvert.getAllAdverts()).willReturn(result);

        mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
                .andExpect(view().name("main_view"));
    }


    @Test
    public void whenNotAuthorizedAndGetMainViewThenReturnMainPage() throws Exception {
        User user = new User("ivan", "123", "Ivan", "Ivanov", "123", "email");
        Car car = new Car(100, 1999, "vin", "red", new CarBrand("BMW"), "Model1", new CarBody("TestBody"), new Engine("TestEngine"), 2.5, new Transmission("TesrTr"));
        Advert advert = new Advert("10.10.2019", car, user, false);
        List<Advert> list = List.of(advert);
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given(validateAdvert.getAllAdverts()).willReturn(result);

        mockMvc.perform(get("/login").accept(MediaType.TEXT_HTML))
                .andExpect(view().name("main_view"));
    }


    @Test
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    public void whenAuthorizedAndGetIssueAdvertViewThenCreateAdvertPage() throws Exception {
        mockMvc.perform(post("/create").accept(MediaType.TEXT_HTML))
                .andExpect(view().name("create_advert"));
    }

    @Test
    @WithAnonymousUser
    public void whenNotAuthorizedAndGetIssueAdvertViewThenReturnMainPage() throws Exception {
        User user = new User("ivan", "123", "Ivan", "Ivanov", "123", "email");
        Car car = new Car(100, 1999, "vin", "red", new CarBrand("BMW"), "Model1", new CarBody("TestBody"), new Engine("TestEngine"), 2.5, new Transmission("TesrTr"));
        Advert advert = new Advert("10.10.2019", car, user, false);
        List<Advert> list = List.of(advert);
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given(validateAdvert.getAllAdverts()).willReturn(result);
        mockMvc.perform(get("/create").accept(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenNotAuthorizedAndGetRegisterViewThenReturnRegisterPage() throws Exception {
        mockMvc.perform(get("/show_register").accept(MediaType.TEXT_HTML))
                .andExpect(view().name("register"));
    }




}