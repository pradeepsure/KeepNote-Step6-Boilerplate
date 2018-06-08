package com.stackroute.keepnote.test.repository;

import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;


    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserId("Jhon123");
        user.setUserName("Jhon Simon");
        user.setUserMobile("9898989898");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
    }

    @After
    public void tearDown() throws Exception {

        userRepository.deleteAll();

    }

    @Test
    public void registerUserTest() {

        userRepository.insert(user);
        User fetcheduser = userRepository.findById("Jhon123").get();
        Assert.assertEquals(user.getUserId(), fetcheduser.getUserId());

    }

    @Test(expected = NoSuchElementException.class)
    public void deleteUserTest() {
        userRepository.insert(user);
        User fetcheduser = userRepository.findById("Jhon123").get();
        Assert.assertEquals("Jhon123", fetcheduser.getUserId());
        userRepository.delete(fetcheduser);
        fetcheduser = userRepository.findById("Jhon123").get();

    }

    @Test
    public void updateUserTest() {
        userRepository.insert(user);
        User fetcheduser = userRepository.findById("Jhon123").get();
        fetcheduser.setUserPassword("987654321");
        userRepository.save(fetcheduser);
        fetcheduser = userRepository.findById("Jhon123").get();
        Assert.assertEquals("987654321", fetcheduser.getUserPassword());
    }

    @Test
    public void getUserByIdTest() {
        userRepository.insert(user);
        User fetcheduser = userRepository.findById("Jhon123").get();
        Assert.assertEquals(user.getUserId(),fetcheduser.getUserId());

    }
}
