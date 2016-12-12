package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bijanfazeli on 12/12/16.
 */
public class EventTest {

    private Event mEvent;

    @Before
    public void setUp() throws Exception {

        mEvent = new Event();
        mEvent.setName("Test");
        mEvent.setOwnerId(1);
        mEvent.setStartDate("12-1-16 07:45 am");
        mEvent.setEndDate("12-1-16 10:45 am");
        mEvent.setLocation("2701 Fairview Road Costa Mesa California 92626");
        mEvent.setDescription("Need volunteers to facilitate with reviewing and handling of books.");
    }

    @After
    public void tearDown() throws Exception {
        mEvent = null;
    }

    @Test
    public void getOwnerId() throws Exception {
        assertNotSame(2, mEvent.getOwnerId());
    }

    @Test
    public void getId() throws Exception {
        assertNotNull("getId test", mEvent.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Test", mEvent.getName());
    }

    @Test
    public void getDescription() throws Exception {
        assertNotSame("Don't need volunteers", mEvent.getDescription());
    }

    @Test
    public void getLocation() throws Exception {
        assertEquals("2701 Fairview Road Costa Mesa California 92626", mEvent.getLocation());
    }

    @Test
    public void getStartDate() throws Exception {
        assertEquals("12-1-16 07:45 am", mEvent.getStartDate());
    }

    @Test
    public void getEndDate() throws Exception {
        assertNotNull(mEvent.getEndDate());
    }

    @Test
    public void getDuration() throws Exception {
        assertEquals("0 days, 3 hours and 0 minutes", mEvent.getDuration());
    }

    @Test
    public void invalidSetup() throws Exception {
        assertEquals(-1, mEvent.invalidSetup());
    }

    @Test
    public void eventPassed() throws Exception {
        assertTrue(mEvent.eventPassed());
    }

    @Test
    public void getStart() throws Exception {
        assertNotNull(mEvent.getStart());
    }

    @Test
    public void getEnd() throws Exception {
        assertNotNull(mEvent.getEnd());
    }
}