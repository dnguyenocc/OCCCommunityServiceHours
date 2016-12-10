package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

/**
 * Created by Huy Ho on 12/10/2016.
 */
public class RecoveryTest {
    private Recovery mRecovery;

    @Before
    public void setUp() throws Exception {

        mRecovery = new Recovery();
        mRecovery.setUserId(123);
        mRecovery.setQuestion1("what is your first pet?");
        mRecovery.setQuestion2("what is your favorite game?");
        mRecovery.setAnswer1("dog");
        mRecovery.setAnswer2("HereWeGo");
        mRecovery.setStatus("Never Change");


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getStatus() throws Exception {
        assertEquals("Never Change", mRecovery.getStatus());
    }

    @Test
    public void getId() throws Exception {
        assertNotSame(23, mRecovery.getId());
    }

    @Test
    public void getUserId() throws Exception {
        assertEquals(123, mRecovery.getUserId());
    }

    @Test
    public void getQuestion1() throws Exception {
        assertEquals("what is your first pet?", mRecovery.getQuestion1());
    }

    @Test
    public void getQuestion2() throws Exception {
        assertEquals("what is your favorite game?", mRecovery.getQuestion2());
    }

    @Test
    public void getAnswer1() throws Exception {
        assertEquals("dog", mRecovery.getAnswer1());

    }

    @Test
    public void getAnswer2() throws Exception {
        assertEquals("HereWeGo", mRecovery.getAnswer2());
    }

}