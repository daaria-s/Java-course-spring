package edu.java.bot.utils;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersUtilsTest {

    @Test
    public void usersUtilsTest() {
        UsersUtils.addNewUser(1);
        UsersUtils.addNewUser(2);
        UsersUtils.addNewUser(3);

        assertTrue(UsersUtils.checkUser(1));
        assertTrue(UsersUtils.checkUser(2));
        assertTrue(UsersUtils.checkUser(3));

        assertFalse(UsersUtils.checkUser(100));
        assertFalse(UsersUtils.checkUser(200));
        assertFalse(UsersUtils.checkUser(300));
    }
}
