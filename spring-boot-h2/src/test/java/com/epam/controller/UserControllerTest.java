package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UserControllerTest {

    @Mock
    UserService userService;

    UserController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new UserController(userService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"));
    }

    @Test
    public void getIndexPage() throws Exception {
        //given
        User user = new User();
        user.setId(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        //when
        ModelAndView viewName = controller.getUserPage(1L);

        //then
        assertEquals("user", viewName.getViewName());
        verify(userService, times(1)).getUserById(anyLong());
    }

}