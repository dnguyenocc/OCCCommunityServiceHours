package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by duyng on 12/9/2016.
 */
public class ParticipationTest {
    private Participation mParticipation;
    private User mUser;
    private Event mEvent;
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
        mEvent = new Event();
        mEvent.setName("Test");
        mEvent.setOwnerId(1);
        mEvent.setStartDate("12-1-16 07:45 am");
        mEvent.setEndDate("12-1-16 10:45 am");
        mEvent.setLocation("2701 Fairview Road Costa Mesa California 92626");
        mEvent.setDescription("Need volunteers to facilitate with reviewing and handling of books.");
        mParticipation = new Participation(1, Participation.VALIDATED, true, 0.0f, null,
                null, null);
        mParticipation.setResponsibilities("Have fun");
        mParticipation.setValidationRequested(false);
        mParticipation.setStatusCode(Participation.REGISTERED);
        mParticipation.setServiceHours(2.0f);
        mParticipation.setUser(mUser);
        mParticipation.setEvent(mEvent);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(1,mParticipation.getId());
    }

    @Test
    public void getStatusCode() throws Exception {
        assertEquals(Participation.REGISTERED,mParticipation.getStatusCode());
    }

    @Test
    public void getValidationRequested() throws Exception {
        assertEquals(false,mParticipation.getValidationRequested());
    }

    @Test
    public void getServiceHours() throws Exception {
        assertEquals(true,(mParticipation.getServiceHours()-2.0f)==0 );
    }

    @Test
    public void getResponsibilities() throws Exception {
        assertEquals("Have fun",mParticipation.getResponsibilities());
    }

    @Test
    public void getEvent() throws Exception {
        assertEquals(mEvent,mParticipation.getEvent());
    }

    @Test
    public void getUser() throws Exception {
        assertEquals(mUser,mParticipation.getUser());
    }

    @Test
    public void setStatusCode() throws Exception {

    }

    @Test
    public void setValidationRequested() throws Exception {

    }

    @Test
    public void setServiceHours() throws Exception {

    }

    @Test
    public void setResponsibilities() throws Exception {

    }

    @Test
    public void setEvent() throws Exception {

    }

    @Test
    public void setUser() throws Exception {

    }

}