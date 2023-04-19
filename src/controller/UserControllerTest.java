package controller;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.User;

public class UserControllerTest {

    private UserController controller;
    private User user;

    @Before
    public void setUp() throws Exception {
        controller = new UserController();
        user = new User("testuser", "password", "John", "Doe");
    }

    @Test
    public void testCreateProfile() {
        controller.createProfile(user);
        assertTrue(controller.login("testuser", "password"));
    }

    @Test
    public void testLogin() {
        controller.createProfile(user);
        assertTrue(controller.login("testuser", "password"));
        assertFalse(controller.login("testuser", "wrongpassword"));
    }

    @Test
    public void testEditProfile() {
        User user1 = new User("username1", "password1", "John", "Doe");
        User user2 = new User("username2", "password2", "Jane", "Doe");

        UserController controller = new UserController();
        controller.createProfile(user1);
        controller.createProfile(user2);

        user2.setUsername("new_username");
        user2.setPassword("new_password");
        user2.setFirstName("New");
        user2.setLastName("Name");

        controller.editProfile(user2);

        User editedUser = controller.getUsers().get(1);

        assertEquals("new_username", editedUser.getUsername());
        assertEquals("new_password", editedUser.getPassword());
        assertEquals("New", editedUser.getFirstName());
        assertEquals("Name", editedUser.getLastName());
    }


    @Test
    public void testDeleteProfile() {
        controller.createProfile(user);
        controller.deleteProfile(user);
        assertFalse(controller.login("testuser", "password"));
    }

}
