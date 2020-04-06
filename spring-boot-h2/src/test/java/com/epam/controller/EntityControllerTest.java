package com.epam.controller;

import com.epam.converter.PdfMessageConverter;
import com.epam.model.User;
import com.epam.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntityControllerTest {

    @Mock
    UserService userService;

    EntityController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new EntityController(userService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(userService.getUsers()).thenReturn(new ArrayList<User>());
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void givenConsumingJson_whenReadingTheFoo_thenCorrect() {
        String URI = "http://localhost:8080/user/{id}";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_PDF));
        headers.setContentType(MediaType.APPLICATION_PDF);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        final ResponseEntity<User> exchange = restTemplate.exchange(URI, HttpMethod.GET, entity, User.class, "1");
        User user = exchange.getBody();

        assertThat(user, notNullValue());
    }
    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters =
                new ArrayList<HttpMessageConverter<?>>();
        converters.add(new PdfMessageConverter());
        return converters;
    }
}
