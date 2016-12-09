package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hho65 on 11/22/2016.
 */


class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //TASK: DEFINE THE DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    static final String DATABASE_NAME = "OCC COMMUNITY SERVICE"; //remember change to public later
    private static final int DATABASE_VERSION = 1;

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE USERS TABLE
    private static final String USERS_TABLE = "Courses";
    private static final String USERS_KEY_FIELD_ID = "_id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_EMAIL ="email";
    private static final String FIELD_PHONE_NUMBER = "phone_number";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_HOURS = "hours";
    private static final String FIELD_ROLE = "role";
    private static final String FIELD_IMAGE_NAME = "image_name";


    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE LOGIN TABLE
    private static final String LOGIN_TABLE = "Login";
    private static final String LOGIN_KEY_FIELD_ID = "_id";
    private static final String FIELD_LOGIN_USER_ID = "user_id";
    private static final String FIELD_LOGIN_USER_ROLE = "role";

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE QUESTION TABLE
    private static final String QUESTIONS_TABLE = "Question";
    private static final String QUESTION_KEY_FIELD_ID = "_id";
    private static final String QUESTION_1 = "question1";
    private static final String QUESTION_2 = "question2";

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE RECOVERY TABLE
    private static final String RECOVERY_TABLE = "Recovery";
    private static final String RECOVERY_KEY_FIELD_ID = "_id";
    private static final String FIELD_RECOVERY_USER_ID = "user_id";
    private static final String FIELD_RECOVERY_USER_QUESTION_1 = "question1";
    private static final String FIELD_RECOVERY_USER_QUESTION_2 = "question2";
    private static final String FIELD_RECOVERY_USER_ANSWER_1 = "answer1";
    private static final String FIELD_RECOVERY_USER_ANSWER_2 = "answer2";
    private static final String FIELD_RECOVERY_USER_TIMES = "times";




    // FIELD NAMES FOR THE EVENTS TABLE
    private static final String EVENTS_TABLE = "Events";
    private static final String EVENTS_KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_OWNER_ID = "owner_id";
    private static final String FIELD_START_DATE = "start_date";
    private static final String FIELD_END_DATE = "end_date";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_EVENT_IMAGE_NAME = "image_name";



    // FIELD NAMES FOR THE PARTICIPATIONS TABLE
    private static final String PARTICIPATIONS_TABLE = "Participations";
    private static final String PARTICIPATIONS_KEY_FIELD_ID = "id";
    private static final String FIELD_STATUS_CODE = "status_code";
    private static final String FIELD_VALIDATION_REQUESTED = "validation_requested";
    private static final String FIELD_SERVICE_HOURS = "service_hours";
    private static final String FIELD_RESPONSIBILITIES = "responsibilities";
    private static final String FIELD_USER_ID = "user_id";
    private static final String FIELD_EVENT_ID = "event_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createQuery = "CREATE TABLE " + USERS_TABLE + "("
                + USERS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_FIRST_NAME + " TEXT, "
                + FIELD_LAST_NAME + " TEXT, "
                + FIELD_USERNAME + " TEXT UNIQUE, "
                + FIELD_EMAIL + " TEXT, "
                + FIELD_PHONE_NUMBER + " TEXT, "
                + FIELD_PASSWORD + " TEXT, "
                + FIELD_HOURS + " REAL, "
                + FIELD_ROLE + " INTEGER, "
                + FIELD_IMAGE_NAME + " TEXT" + ")";
        database.execSQL(createQuery);

        // Create the Events table
        createQuery = "CREATE TABLE " + QUESTIONS_TABLE + "("
                + QUESTION_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUESTION_1 + " TEXT, "
                + QUESTION_2 + " TEXT" + ")";
        database.execSQL(createQuery);

        // Create the Events table
        createQuery = "CREATE TABLE " + LOGIN_TABLE + "("
                + LOGIN_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_LOGIN_USER_ID + " INTEGER, "
                + FIELD_LOGIN_USER_ROLE + " INTEGER" + ")";
        database.execSQL(createQuery);

        // Create the Recovery table
        createQuery = "CREATE TABLE " + RECOVERY_TABLE + "("
                + RECOVERY_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_RECOVERY_USER_ID + " INTEGER, "
                + FIELD_RECOVERY_USER_QUESTION_1 + " TEXT, "
                + FIELD_RECOVERY_USER_QUESTION_2 + " TEXT, "
                + FIELD_RECOVERY_USER_ANSWER_1 + " TEXT, "
                + FIELD_RECOVERY_USER_ANSWER_2 + " TEXT, "
                + FIELD_RECOVERY_USER_TIMES + " INTEGER" + ")";
        database.execSQL(createQuery);

        // Create the Events table
        createQuery = "CREATE TABLE " + EVENTS_TABLE + "("
                + EVENTS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_OWNER_ID + " INTEGER, "
                + FIELD_START_DATE + " TEXT, "
                + FIELD_END_DATE + " TEXT, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_LOCATION + " TEXT, "
                + FIELD_EVENT_IMAGE_NAME + " TEXT" + ")";
        database.execSQL(createQuery);



        // Create the Participation table
        createQuery = "CREATE TABLE " + PARTICIPATIONS_TABLE + "("
                + PARTICIPATIONS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_STATUS_CODE + " INTEGER, "
                + FIELD_VALIDATION_REQUESTED + " INTEGER, "
                + FIELD_SERVICE_HOURS + " REAL, "
                + FIELD_RESPONSIBILITIES +" TEXT,"
                + FIELD_USER_ID + " INTEGER, "
                + FIELD_EVENT_ID + " INTEGER, "
                + "FOREIGN KEY(" + FIELD_USER_ID + ") REFERENCES "
                + USERS_TABLE + "(" + USERS_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_EVENT_ID + ") REFERENCES "
                + EVENTS_TABLE + "(" + EVENTS_KEY_FIELD_ID + ")" +
                ")";
        database.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + RECOVERY_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + PARTICIPATIONS_TABLE);

        onCreate(database);
    }

    //********** QUESTION TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE
    public void addQuestion(String q1,String q2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QUESTION_1, q1);
        values.put(QUESTION_2, q2);
        db.insert(QUESTIONS_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }
    public ArrayList<String> getAllQuestions(int typeNumberQuestion) {
        ArrayList<String> questionList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                QUESTIONS_TABLE,
                new String[]{QUESTION_KEY_FIELD_ID,
                        QUESTION_1,
                        QUESTION_2},
                null,
                null,
                null, null, null, null);

        int index;
        if(typeNumberQuestion == 1){
            index = 1;
        }
        else {
            index = 2;
        }
        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {

            do {

                String q = cursor.getString(index);
                questionList.add(q);

            } while (cursor.moveToNext());
        }
        return questionList;
    }


    //********** RECOVERY TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE

    public void addRecoveryUser(Recovery recovery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_RECOVERY_USER_ID, recovery.getUserId());
        values.put(FIELD_RECOVERY_USER_QUESTION_1, recovery.getQuestion1());
        values.put(FIELD_RECOVERY_USER_QUESTION_1, recovery.getQuestion2());
        values.put(FIELD_RECOVERY_USER_ANSWER_1, recovery.getAnswer1());
        values.put(FIELD_RECOVERY_USER_ANSWER_2, recovery.getAnswer2());
        values.put(FIELD_RECOVERY_USER_TIMES, 0);
        db.insert(RECOVERY_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public Recovery getRecoveryUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = getUserIdByEmail(email);
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{RECOVERY_KEY_FIELD_ID,
                        FIELD_RECOVERY_USER_ID,
                        FIELD_RECOVERY_USER_QUESTION_1,
                        FIELD_RECOVERY_USER_QUESTION_2,
                        FIELD_RECOVERY_USER_ANSWER_1,
                        FIELD_RECOVERY_USER_ANSWER_2,
                        FIELD_RECOVERY_USER_TIMES},
                FIELD_RECOVERY_USER_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Recovery recovery =
                new Recovery(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6));
        cursor.close();
        db.close();
        return recovery;
    }
    public void updateRecoveryUser(Recovery recovery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_RECOVERY_USER_ID, recovery.getUserId());
        values.put(FIELD_RECOVERY_USER_QUESTION_1, recovery.getQuestion1());
        values.put(FIELD_RECOVERY_USER_QUESTION_1, recovery.getQuestion2());
        values.put(FIELD_RECOVERY_USER_ANSWER_1, recovery.getAnswer1());
        values.put(FIELD_RECOVERY_USER_ANSWER_2, recovery.getAnswer2());
        values.put(FIELD_RECOVERY_USER_TIMES, recovery.getTimes()+1);


        db.update(RECOVERY_TABLE, values, RECOVERY_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(recovery.getId())});
        db.close();
    }
    //********** LOGIN TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE
    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID,
                        FIELD_FIRST_NAME,
                        FIELD_LAST_NAME,
                        FIELD_USERNAME,
                        FIELD_EMAIL,
                        FIELD_PHONE_NUMBER,
                        FIELD_PASSWORD,
                        FIELD_HOURS,
                        FIELD_ROLE,
                        FIELD_IMAGE_NAME},
                FIELD_EMAIL + "=?",
                new String[]{email},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        int userId = cursor.getInt(4);

        cursor.close();
        db.close();
        return userId;
    }

    public void addLoginUser(int id, int role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_LOGIN_USER_ID, id);
        values.put(FIELD_LOGIN_USER_ROLE, role);

        db.insert(LOGIN_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public User getLoginUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOGIN_TABLE,
                new String[]{LOGIN_KEY_FIELD_ID,
                        FIELD_LOGIN_USER_ID,
                        FIELD_LOGIN_USER_ROLE},
                null,
                null,
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        int id = cursor.getInt(1);

        User user = getUser(id);

        cursor.close();

        db.close();
        return user;
    }


    public void logout(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(LOGIN_TABLE, FIELD_LOGIN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getmId())});
        db.close();
    }


    //********** USER TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE

    public boolean checkPhoneNumber(String phoneNumber)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = new String[]{phoneNumber};


        Cursor  c = db.rawQuery("select * from "+USERS_TABLE+ " where " +FIELD_PHONE_NUMBER+ " =? ", selectionArgs);
        c.moveToFirst();
        if(c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public boolean checkLogin(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = new String[]{username, password};


        Cursor  c = db.rawQuery("select * from "+USERS_TABLE+ " where " +FIELD_USERNAME+ " =? and "+FIELD_PASSWORD+" =? ", selectionArgs);
        c.moveToFirst();
        if(c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public boolean checkUserName(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = new String[]{username};


        Cursor  c = db.rawQuery("select * from "+USERS_TABLE+ " where " +FIELD_USERNAME+ " =? ", selectionArgs);
        c.moveToFirst();
        if(c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = new String[]{email};


        Cursor  c = db.rawQuery("select * from "+USERS_TABLE+ " where " +FIELD_EMAIL+ " =? ", selectionArgs);
        c.moveToFirst();
        if(c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FIRST_NAME, user.getFirstName());
        values.put(FIELD_LAST_NAME, user.getLastName());
        values.put(FIELD_USERNAME, user.getmUserName());
        values.put(FIELD_EMAIL,user.getmEmail());
        values.put(FIELD_PHONE_NUMBER,user.getmPhoneNum());
        values.put(FIELD_PASSWORD,user.getmPassWord());
        values.put(FIELD_HOURS,user.getmHours());
        values.put(FIELD_ROLE,user.getmRole());
        values.put(FIELD_IMAGE_NAME,user.getmImageUri().toString());

        db.insert(USERS_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }


    public ArrayList<User> getAllUser() {
        ArrayList<User> usersList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = database.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID,
                        FIELD_FIRST_NAME,
                        FIELD_LAST_NAME,
                        FIELD_USERNAME,
                        FIELD_EMAIL,
                        FIELD_PHONE_NUMBER,
                        FIELD_PASSWORD,
                        FIELD_HOURS,
                        FIELD_ROLE,
                        FIELD_IMAGE_NAME},
                null,
                null,
                null, null, null, null);


        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                User user =
                        new User(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getDouble(7),
                                cursor.getInt(8),
                                Uri.parse(cursor.getString(9)));
                usersList.add(user);
            } while (cursor.moveToNext());
        }
        return usersList;
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(USERS_TABLE, USERS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(user.getmId())});
        db.close();
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS_TABLE, null, null);
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FIRST_NAME, user.getFirstName());
        values.put(FIELD_LAST_NAME, user.getLastName());
        values.put(FIELD_USERNAME, user.getmUserName());
        values.put(FIELD_EMAIL,user.getmEmail());
        values.put(FIELD_PHONE_NUMBER,user.getmPhoneNum());
        values.put(FIELD_PASSWORD,user.getmPassWord());
        values.put(FIELD_HOURS,user.getmHours());
        values.put(FIELD_ROLE,user.getmRole());
        values.put(FIELD_IMAGE_NAME,user.getmImageUri().toString());


        db.update(USERS_TABLE, values, USERS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(user.getmId())});
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID,
                        FIELD_FIRST_NAME,
                        FIELD_LAST_NAME,
                        FIELD_USERNAME,
                        FIELD_EMAIL,
                        FIELD_PHONE_NUMBER,
                        FIELD_PASSWORD,
                        FIELD_HOURS,
                        FIELD_ROLE,
                        FIELD_IMAGE_NAME},
                USERS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user =
                new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        Uri.parse(cursor.getString(9)));

        cursor.close();

        db.close();
        return user;
    }


    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID,
                        FIELD_FIRST_NAME,
                        FIELD_LAST_NAME,
                        FIELD_USERNAME,
                        FIELD_EMAIL,
                        FIELD_PHONE_NUMBER,
                        FIELD_PASSWORD,
                        FIELD_HOURS,
                        FIELD_ROLE,
                        FIELD_IMAGE_NAME},
                FIELD_USERNAME + "=?",
                new String[]{username},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user =
                new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        Uri.parse(cursor.getString(9)));

        cursor.close();

        db.close();
        return user;
    }


    //********** EVENTS TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, event.getName());
        values.put(FIELD_OWNER_ID, event.getOwnerId());
        values.put(FIELD_START_DATE, event.getStartDate());
        values.put(FIELD_END_DATE, event.getEndDate());
        values.put(FIELD_DESCRIPTION, event.getDescription());
        values.put(FIELD_LOCATION, event.getLocation());
        values.put(FIELD_EVENT_IMAGE_NAME,event.getImageUri().toString());

        db.insert(EVENTS_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                EVENTS_TABLE,
                new String[]{EVENTS_KEY_FIELD_ID, FIELD_NAME, FIELD_OWNER_ID, FIELD_START_DATE, FIELD_END_DATE,
                        FIELD_DESCRIPTION, FIELD_LOCATION, FIELD_EVENT_IMAGE_NAME},
                EVENTS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Event event =
                new Event(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        Uri.parse(cursor.getString(7)));
        cursor.close();
        db.close();
        return event;
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                EVENTS_TABLE,
                new String[]{EVENTS_KEY_FIELD_ID, FIELD_NAME, FIELD_OWNER_ID, FIELD_START_DATE, FIELD_END_DATE,
                        FIELD_DESCRIPTION, FIELD_LOCATION, FIELD_EVENT_IMAGE_NAME},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                Event event =
                        new Event (cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                Uri.parse(cursor.getString(7)));
                eventsList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return eventsList;
    }

    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete the specific event
        db.delete(EVENTS_TABLE, EVENTS_KEY_FIELD_ID + " = ? ",
                new String[]{String.valueOf(event.getId())});

        db.close();
    }

    public void deleteAllEvents() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EVENTS_TABLE, null, null);
        db.close();
    }

    public void updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, event.getName());
        values.put(FIELD_OWNER_ID, event.getOwnerId());
        values.put(FIELD_START_DATE, event.getStartDate());
        values.put(FIELD_END_DATE, event.getEndDate());
        values.put(FIELD_DESCRIPTION, event.getDescription());
        values.put(FIELD_LOCATION, event.getLocation());
        values.put(FIELD_EVENT_IMAGE_NAME,event.getImageUri().toString());


        db.update(EVENTS_TABLE, values, EVENTS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(event.getId())});
        db.close();
    }

    public ArrayList<Event> getAllEventsByOwnerId(int ownerId)
    {
        ArrayList<Event> ownerEventsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                EVENTS_TABLE,
                new String[]{EVENTS_KEY_FIELD_ID, FIELD_NAME, FIELD_OWNER_ID, FIELD_START_DATE, FIELD_END_DATE,
                        FIELD_DESCRIPTION, FIELD_LOCATION, FIELD_EVENT_IMAGE_NAME},
                FIELD_OWNER_ID + "=?",
                new String[]{String.valueOf(ownerId)},
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                Event event =
                        new Event (cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                Uri.parse(cursor.getString(7)));
                ownerEventsList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return ownerEventsList;
    }

    //********** PARTICIPATION TABLE OPERATIONS:  ADD, GETALL, EDIT, DELETE

    public void addParticipation(int statusCode, boolean validationRequested,
                                float serviceHours, String responsibilities,
                                int userId, int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_STATUS_CODE, statusCode);
        values.put(FIELD_VALIDATION_REQUESTED, validationRequested);
        values.put(FIELD_SERVICE_HOURS, serviceHours);
        values.put(FIELD_RESPONSIBILITIES, responsibilities);
        values.put(FIELD_USER_ID,userId);
        values.put(FIELD_EVENT_ID,eventId);

        db.insert(PARTICIPATIONS_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public ArrayList<Participation> getAllParticipations() {
        ArrayList<Participation> participationsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = database.query(
                PARTICIPATIONS_TABLE,
                new String[]{PARTICIPATIONS_KEY_FIELD_ID,
                        FIELD_STATUS_CODE,
                        FIELD_VALIDATION_REQUESTED,
                        FIELD_SERVICE_HOURS,
                        FIELD_RESPONSIBILITIES,
                        FIELD_USER_ID,
                        FIELD_EVENT_ID},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                User user = getUser(cursor.getInt(5));
                Event event = getEvent(cursor.getInt(6));
                Participation participation = new Participation(cursor.getInt(0),
                        cursor.getInt(1), cursor.getInt(2)!=0, cursor.getFloat(3),cursor.getString(4),
                        user,
                        event);

                participationsList.add(participation);
            } while (cursor.moveToNext());
        }
        return participationsList;
    }

    public ArrayList<Participation> getValidatedParticipationsByUserId(int userId) {
        ArrayList<Participation> participationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = db.query(
                PARTICIPATIONS_TABLE,
                new String[]{PARTICIPATIONS_KEY_FIELD_ID, FIELD_STATUS_CODE, FIELD_VALIDATION_REQUESTED,
                        FIELD_SERVICE_HOURS, FIELD_RESPONSIBILITIES,FIELD_USER_ID, FIELD_EVENT_ID},
                FIELD_USER_ID + "=?" + " AND " + FIELD_STATUS_CODE +" =? ",
                new String[]{String.valueOf(userId),String.valueOf(Participation.VALIDATED)},
                null, null, null, null);


        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                User user = getUser(cursor.getInt(5));
                Event event = getEvent(cursor.getInt(6));
                Participation participation = new Participation(cursor.getInt(0),
                        cursor.getInt(1), cursor.getInt(2)!=0, cursor.getFloat(3),cursor.getString(4),
                        user,
                        event);

                participationsList.add(participation);
            } while (cursor.moveToNext());
        }
        return participationsList;
    }

    public ArrayList<Participation> getAllParticipationsByUserId(int userId) {
        ArrayList<Participation> participationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = db.query(
                PARTICIPATIONS_TABLE,
                new String[]{PARTICIPATIONS_KEY_FIELD_ID, FIELD_STATUS_CODE, FIELD_VALIDATION_REQUESTED,
                        FIELD_SERVICE_HOURS, FIELD_RESPONSIBILITIES,FIELD_USER_ID, FIELD_EVENT_ID},
                FIELD_USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null, null, null, null);


        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                User user = getUser(cursor.getInt(5));
                Event event = getEvent(cursor.getInt(6));
                Participation participation = new Participation(cursor.getInt(0),
                        cursor.getInt(1), cursor.getInt(2)!=0, cursor.getFloat(3),cursor.getString(4),
                        user,
                        event);

                participationsList.add(participation);
            } while (cursor.moveToNext());
        }
        return participationsList;
    }


    public ArrayList<Participation> getRequestParticipations() {
        ArrayList<Participation> participationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = db.query(
                PARTICIPATIONS_TABLE,
                new String[]{PARTICIPATIONS_KEY_FIELD_ID,
                        FIELD_STATUS_CODE,
                        FIELD_VALIDATION_REQUESTED,
                        FIELD_SERVICE_HOURS,
                        FIELD_RESPONSIBILITIES,
                        FIELD_USER_ID,
                        FIELD_EVENT_ID},
                FIELD_VALIDATION_REQUESTED + "=?",
                new String[]{String.valueOf(1)},
                null, null, null, null);


        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                User user = getUser(cursor.getInt(5));
                Event event = getEvent(cursor.getInt(6));
                Participation participation = new Participation(cursor.getInt(0),
                        cursor.getInt(1), cursor.getInt(2)!=0, cursor.getFloat(3),cursor.getString(4),
                        user,
                        event);

                participationsList.add(participation);
            } while (cursor.moveToNext());
        }
        return participationsList;
    }

    public boolean checkParticipation(int userId, int eventId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = new String[]{String.valueOf(userId),String.valueOf(eventId)};


        Cursor  c = db.rawQuery("select * from "+PARTICIPATIONS_TABLE+ " where " +FIELD_USER_ID+ " =? "
                +" AND " +FIELD_EVENT_ID+" =? ", selectionArgs);
        c.moveToFirst();
        if(c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public void deleteParticipation(Participation participation) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(PARTICIPATIONS_TABLE, PARTICIPATIONS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(participation.getId())});
        db.close();
    }

    public void deleteAllParticipations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PARTICIPATIONS_TABLE, null, null);
        db.close();
    }

    public void updateParticipation(Participation participation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PARTICIPATIONS_KEY_FIELD_ID, participation.getId());
        values.put(FIELD_STATUS_CODE, participation.getStatusCode());
        values.put(FIELD_VALIDATION_REQUESTED,participation.getValidationRequested());
        values.put(FIELD_SERVICE_HOURS,participation.getServiceHours());
        values.put(FIELD_RESPONSIBILITIES,participation.getResponsibilities());
        values.put(FIELD_USER_ID, participation.getUser().getmId());
        values.put(FIELD_EVENT_ID, participation.getEvent().getId());

        db.update(PARTICIPATIONS_TABLE, values, PARTICIPATIONS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(participation.getId())});
        db.close();
    }

    public Participation getParticipation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                PARTICIPATIONS_TABLE,
                new String[]{PARTICIPATIONS_KEY_FIELD_ID, FIELD_STATUS_CODE, FIELD_VALIDATION_REQUESTED,
                        FIELD_SERVICE_HOURS, FIELD_RESPONSIBILITIES,FIELD_USER_ID, FIELD_EVENT_ID},
                PARTICIPATIONS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = getUser(cursor.getInt(5));
        Event event = getEvent(cursor.getInt(6));
        Participation participation = new Participation(cursor.getInt(0),
                cursor.getInt(1), cursor.getInt(2)!=0, cursor.getFloat(3),cursor.getString(4),
                user,
                event);

        db.close();
        return participation;
    }

    public Participation getParticipation(int userId, int eventId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                PARTICIPATIONS_TABLE,
                new String[]{PARTICIPATIONS_KEY_FIELD_ID, FIELD_STATUS_CODE, FIELD_VALIDATION_REQUESTED,
                        FIELD_SERVICE_HOURS, FIELD_RESPONSIBILITIES,FIELD_USER_ID, FIELD_EVENT_ID},
                FIELD_USER_ID + "=?" + " AND " + FIELD_EVENT_ID +" =? ",
                new String[]{String.valueOf(userId),String.valueOf(eventId)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = getUser(cursor.getInt(5));
        Event event = getEvent(cursor.getInt(6));
        Participation participation = new Participation(cursor.getInt(0),
                cursor.getInt(1), cursor.getInt(2)!=0, cursor.getFloat(3),cursor.getString(4),
                user,
                event);

        db.close();
        return participation;
    }


    public boolean importUsersFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 9) {
                    Log.d("OCC Service Hours", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                String username = fields[3].trim();
                String email = fields[4].trim();
                String phoneNumber = fields[5].trim();
                String password = fields[6].trim();
                Double hour = Double.valueOf(fields[7].trim());
                int role = Integer.parseInt(fields[8].trim());
                //TODO image URI later
                Uri imageURI = LoginActivity.getUriToResource(mContext,R.drawable.default_avatar);
                addUser(new User(id, firstName, lastName, username,email,phoneNumber,password,hour,role,imageURI));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean importEventsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 8) {
                    Log.d("OCC Service Hours", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String eventName = fields[1].trim();
                int ownerId = Integer.parseInt(fields[2].trim());
                String startDate = fields[3].trim();
                String endDate = fields[4].trim();
                String description = fields[5].trim();
                String location = fields[6].trim();
                //TODO change image name later
                //String imageName = fields[6].trim();

                Uri imageURI = LoginActivity.getUriToResource(mContext,R.drawable.occpirate);
                addEvent(new Event(id, eventName, ownerId, startDate, endDate,description,location,imageURI));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean importParticipationsFromCSV(String csvFileName) {
        AssetManager am = mContext.getAssets();
        InputStream inStream = null;
        try {
            inStream = am.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 7) {
                    Log.d("OCC Service Hours", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                int statusCode = Integer.parseInt(fields[1].trim());
                boolean validationRequested = Integer.parseInt(fields[2].trim()) != 0;
                float serviceHours = Float.parseFloat(fields[3].trim());
                String responsibilities = fields[4].trim();
                int userId = Integer.parseInt(fields[5].trim());
                int eventId = Integer.parseInt(fields[6].trim());
                addParticipation(statusCode, validationRequested, serviceHours, responsibilities, userId, eventId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean importQuestionsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 2) {
                    Log.d("OCC Service Hours", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                String q1 = fields[0].trim();
                String q2 = fields[1].trim();

                addQuestion(q1,q2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
