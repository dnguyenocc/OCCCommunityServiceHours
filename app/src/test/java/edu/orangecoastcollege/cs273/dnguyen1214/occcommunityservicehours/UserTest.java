package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

//import org.junit.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

/**
 * Created by Alex Ho on 11/30/2016.
 */
public class UserTest {

    private User mUser;

    @Before
    public void setUp() throws Exception {

        mUser = new User();
        mUser.setFirstName("Alex");
        mUser.setLastName("Ho");
        mUser.setmUserName("AndroidLover");
        mUser.setmEmail("androidking@gmail.com");
        mUser.setmPhoneNum("4657821342");
        mUser.setmPassWord("12345");
        mUser.setmRole(2);
       // mUser.setmImageUri("");
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void getFirstName() throws Exception {
        assertEquals("Alex", mUser.getFirstName());

    }

    @Test
    public void getLastName() throws Exception {
        assertEquals("Ho", mUser.getLastName());
    }

    @Test
    public void getmUserName() throws Exception {
        assertEquals("AndroidLover", mUser.getmUserName());
    }

    @Test
    public void getmEmail() throws Exception {
        assertEquals("androidking@gmail.com", mUser.getmEmail());
    }

    @Test
    public void getmPhoneNum() throws Exception {
        assertEquals("4657821342", mUser.getmPhoneNum());

    }

    @Test
    public void getmPassWord() throws Exception {
        assertEquals("12345", mUser.getmPassWord());

    }

    @Test
    public void getmImageUri() throws Exception {
        assertNull(mUser.getmImageUri());
    }

    @Test
    public void getmId() throws Exception {
        assertNotSame(213,mUser.getmId());
    }





}