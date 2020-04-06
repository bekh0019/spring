package com.epam.service;

import com.epam.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {
    @Autowired
    UserService userService;

   final int numberOfUsers = 3;

    @Test
    public void testUserListInASCOrder() throws Exception {
        //given
        Optional<List<User>> usersOptional = userService.findUsersOrderByIdAsc(numberOfUsers);
        List<Long> ids = getUserIds(usersOptional);

        //then
       assertEquals(Arrays.asList(1L,2L,3L), ids);
    }

    @Test
    public void testUserListInDESCOrder() throws Exception {
        //given
        Optional<List<User>> usersOptional = userService.findUsersOrderByIdDesc(numberOfUsers);
        List<Long> ids = getUserIds(usersOptional);


        //then
        assertEquals(Arrays.asList(5L,4L,3L), ids);
    }

    private List<Long> getUserIds(Optional<List<User>> usersOptional){
        return usersOptional.map(userList -> userList.stream()
                .map(User::getId)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }
}
