package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by hho65 on 11/22/2016.
 */


class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //TASK: DEFINE THE DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    private static final String DATABASE_NAME = "OCC COMMUNITY SERVICE";
    private static final int DATABASE_VERSION = 1;

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE USERS TABLE
    private static final String USERS_TABLE = "Courses";
    private static final String USERS_KEY_FIELD_ID = "_id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_LAST_NAME = "first_name";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_EMAIL ="email";
    private static final String FIELD_PHONE_NUMBER = "phone_number";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_IMAGE_NAME = "image_name";

    // FIELD NAMES FOR THE EVENTS TABLE
    private static final String EVENTS_TABLE = "Events";
    private static final String EVENTS_KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_MONTH = "month";
    private static final String FIELD_DAY = "day";
    private static final String FIELD_YEAR = "year";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_START_TIME = "start_time";
    private static final String FIELD_END_TIME = "end_time";
    private static final String FIELD_EVENT_IMAGE_NAME = "image_name";


//
//    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE OFFERINGS TABLE
//    private static final String OFFERINGS_TABLE = "Offerings";
//    private static final String OFFERINGS_KEY_FIELD_ID = "crn";
//    private static final String FIELD_SEMESTER_CODE = "semester_code";
//    private static final String FIELD_COURSE_ID = "course_id";
//    private static final String FIELD_INSTRUCTOR_ID = "instructor_id";

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
                + FIELD_USERNAME + " TEXT, "
                + FIELD_EMAIL + " TEXT, "
                + FIELD_PHONE_NUMBER + " TEXT, "
                + FIELD_PASSWORD + " TEXT, "
                + FIELD_IMAGE_NAME + " TEXT" + ")";
        database.execSQL(createQuery);

        // Create the Events table
        createQuery = "CREATE TABLE " + EVENTS_TABLE + "("
                + EVENTS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_MONTH + " INTEGER, "
                + FIELD_DAY + " INTEGER, "
                + FIELD_YEAR + " INTEGER, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_LOCATION + " TEXT, "
                + FIELD_START_TIME + " TEXT, "
                + FIELD_END_TIME + " TEXT, "
                + FIELD_EVENT_IMAGE_NAME + " TEXT" + ")";
        database.execSQL(createQuery);


//
//        createQuery = "CREATE TABLE " + OFFERINGS_TABLE + "("
//                + OFFERINGS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + FIELD_SEMESTER_CODE + " INTEGER, "
//                + FIELD_COURSE_ID + " INTEGER, "
//                + FIELD_INSTRUCTOR_ID + " INTEGER, "
//                + "FOREIGN KEY(" + FIELD_COURSE_ID + ") REFERENCES "
//                + COURSES_TABLE + "(" + COURSES_KEY_FIELD_ID + "), "
//                + "FOREIGN KEY(" + FIELD_INSTRUCTOR_ID + ") REFERENCES "
//                + INSTRUCTORS_TABLE + "(" + INSTRUCTORS_KEY_FIELD_ID + ")" +
//                ")";
//        database.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
//        database.execSQL("DROP TABLE IF EXISTS " + INSTRUCTORS_TABLE);
//        database.execSQL("DROP TABLE IF EXISTS " + OFFERINGS_TABLE);
        onCreate(database);
    }

    //********** USER TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE


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
                new String[]{USERS_KEY_FIELD_ID, FIELD_FIRST_NAME, FIELD_LAST_NAME, FIELD_USERNAME,FIELD_EMAIL,FIELD_EMAIL,FIELD_PHONE_NUMBER,FIELD_PASSWORD,FIELD_IMAGE_NAME},
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
                                Uri.parse(cursor.getString(7)));
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
        values.put(FIELD_EMAIL, user.getmEmail());
        values.put(FIELD_PHONE_NUMBER, user.getmPhoneNum());
        values.put(FIELD_PASSWORD, user.getmPassWord());
        values.put(FIELD_IMAGE_NAME, user.getmImageUri().toString());


        db.update(USERS_TABLE, values, USERS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(user.getmId())});
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID, FIELD_FIRST_NAME, FIELD_LAST_NAME, FIELD_USERNAME,FIELD_EMAIL,FIELD_EMAIL,FIELD_PHONE_NUMBER,FIELD_PASSWORD,FIELD_IMAGE_NAME},
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
                        Uri.parse(cursor.getString(7)));

        cursor.close();

        db.close();
        return user;
    }


    //********** EVENTS TABLE OPERATIONS:  ADD, GET ALL, EDIT, DELETE

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, event.getName());
        values.put(FIELD_MONTH, event.getMonth());
        values.put(FIELD_DAY, event.getDay());
        values.put(FIELD_YEAR, event.getYear());
        values.put(FIELD_DESCRIPTION, event.getDescription());
        values.put(FIELD_LOCATION, event.getLocation());
        values.put(FIELD_START_TIME, event.getStartTime());
        values.put(FIELD_END_TIME, event.getEndTime());
        values.put(FIELD_IMAGE_NAME,event.getImageUri().toString());

        db.insert(EVENTS_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                EVENTS_TABLE,
                new String[]{EVENTS_KEY_FIELD_ID, FIELD_NAME, FIELD_MONTH, FIELD_DAY, FIELD_YEAR,
                        FIELD_DESCRIPTION, FIELD_LOCATION, FIELD_START_TIME, FIELD_END_TIME, FIELD_EVENT_IMAGE_NAME},
                EVENTS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Event event =
                new Event(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        Uri.parse(cursor.getString(9)));
        cursor.close();
        db.close();
        return event;
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                EVENTS_TABLE,
                new String[]{EVENTS_KEY_FIELD_ID, FIELD_NAME, FIELD_MONTH, FIELD_DAY, FIELD_YEAR,
                        FIELD_DESCRIPTION, FIELD_LOCATION, FIELD_START_TIME, FIELD_END_TIME, FIELD_EVENT_IMAGE_NAME},
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
                                cursor.getInt(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8),
                                Uri.parse(cursor.getString(9)));
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
        values.put(FIELD_MONTH, event.getMonth());
        values.put(FIELD_DAY, event.getDay());
        values.put(FIELD_YEAR, event.getYear());
        values.put(FIELD_DESCRIPTION, event.getDescription());
        values.put(FIELD_LOCATION, event.getLocation());
        values.put(FIELD_START_TIME, event.getStartTime());
        values.put(FIELD_END_TIME, event.getEndTime());
        values.put(FIELD_IMAGE_NAME,event.getImageUri().toString());


        db.update(EVENTS_TABLE, values, EVENTS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(event.getId())});
        db.close();
    }
//
//    public ArrayList<Instructor> getAllInstructors() {
//        ArrayList<Instructor> instructorsList = new ArrayList<>();
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.query(
//                INSTRUCTORS_TABLE,
//                new String[]{INSTRUCTORS_KEY_FIELD_ID, FIELD_LAST_NAME, FIELD_FIRST_NAME, FIELD_EMAIL},
//                null,
//                null,
//                null, null, null, null);
//
//        //COLLECT EACH ROW IN THE TABLE
//        if (cursor.moveToFirst()) {
//            do {
//                Instructor instructor =
//                        new Instructor(cursor.getInt(0),
//                                cursor.getString(1),
//                                cursor.getString(2),
//                                cursor.getString(3));
//                instructorsList.add(instructor);
//            } while (cursor.moveToNext());
//        }
//        return instructorsList;
//    }
//
//    public void deleteInstructor(Instructor instructor) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // DELETE THE TABLE ROW
//        db.delete(INSTRUCTORS_TABLE, INSTRUCTORS_KEY_FIELD_ID + " = ?",
//                new String[]{String.valueOf(instructor.getId())});
//        db.close();
//    }
//
//    public void deleteAllInstructors() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(INSTRUCTORS_TABLE, null, null);
//        db.close();
//    }
//
//    public void updateInstructor(Instructor instructor) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(FIELD_FIRST_NAME, instructor.getFirstName());
//        values.put(FIELD_LAST_NAME, instructor.getLastName());
//        values.put(FIELD_EMAIL, instructor.getEmail());
//
//        db.update(INSTRUCTORS_TABLE, values, INSTRUCTORS_KEY_FIELD_ID + " = ?",
//                new String[]{String.valueOf(instructor.getId())});
//        db.close();
//    }
//
//    public Instructor getInstructor(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(
//                INSTRUCTORS_TABLE,
//                new String[]{INSTRUCTORS_KEY_FIELD_ID, FIELD_LAST_NAME, FIELD_FIRST_NAME, FIELD_EMAIL},
//                INSTRUCTORS_KEY_FIELD_ID + "=?",
//                new String[]{String.valueOf(id)},
//                null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Instructor instructor = new Instructor(
//                cursor.getInt(0),
//                cursor.getString(1),
//                cursor.getString(2),
//                cursor.getString(3));
//
//        db.close();
//        return instructor;
//    }
//
//
//    //********** OFFERING TABLE OPERATIONS:  ADD, GETALL, EDIT, DELETE
//
//    public void addOffering(int crn, int semesterCode, int courseId, int instructorId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(OFFERINGS_KEY_FIELD_ID, crn);
//        values.put(FIELD_SEMESTER_CODE, semesterCode);
//        values.put(FIELD_COURSE_ID, courseId);
//        values.put(FIELD_INSTRUCTOR_ID, instructorId);
//
//        db.insert(OFFERINGS_TABLE, null, values);
//
//        // CLOSE THE DATABASE CONNECTION
//        db.close();
//    }
//
//    public ArrayList<Offering> getAllOfferings() {
//        ArrayList<Offering> offeringsList = new ArrayList<>();
//        SQLiteDatabase database = this.getReadableDatabase();
//        //Cursor cursor = database.rawQuery(queryList, null);
//        Cursor cursor = database.query(
//                OFFERINGS_TABLE,
//                new String[]{OFFERINGS_KEY_FIELD_ID, FIELD_SEMESTER_CODE, FIELD_COURSE_ID, FIELD_INSTRUCTOR_ID},
//                null,
//                null,
//                null, null, null, null);
//
//        //COLLECT EACH ROW IN THE TABLE
//        if (cursor.moveToFirst()) {
//            do {
//                Course course = getCourse(cursor.getInt(2));
//                Instructor instructor = getInstructor(cursor.getInt(3));
//                Offering offering = new Offering(cursor.getInt(0),
//                        cursor.getInt(1), course, instructor);
//
//                offeringsList.add(offering);
//            } while (cursor.moveToNext());
//        }
//        return offeringsList;
//    }
//
//    public void deleteOffering(Offering offering) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // DELETE THE TABLE ROW
//        db.delete(OFFERINGS_TABLE, OFFERINGS_KEY_FIELD_ID + " = ?",
//                new String[]{String.valueOf(offering.getCRN())});
//        db.close();
//    }
//
//    public void deleteAllOfferings() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(OFFERINGS_TABLE, null, null);
//        db.close();
//    }
//
//    public void updateOffering(Offering offering) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(FIELD_SEMESTER_CODE, offering.getSemesterCode());
//        values.put(FIELD_COURSE_ID, offering.getCourse().getId());
//        values.put(FIELD_INSTRUCTOR_ID, offering.getInstructor().getId());
//
//        db.update(OFFERINGS_TABLE, values, OFFERINGS_KEY_FIELD_ID + " = ?",
//                new String[]{String.valueOf(offering.getCRN())});
//        db.close();
//    }
//
//    public Offering getOffering(int crn) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(
//                OFFERINGS_TABLE,
//                new String[]{OFFERINGS_KEY_FIELD_ID, FIELD_SEMESTER_CODE, FIELD_COURSE_ID, FIELD_INSTRUCTOR_ID},
//                OFFERINGS_KEY_FIELD_ID + "=?",
//                new String[]{String.valueOf(crn)},
//                null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Course course = getCourse(cursor.getInt(2));
//        Instructor instructor = getInstructor(cursor.getInt(3));
//        Offering offering = new Offering(cursor.getInt(0),
//                cursor.getInt(1), course, instructor);
//
//
//        db.close();
//        return offering;
//    }
//
//
//
//    public Cursor getInstructorNamesCursor() {
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.query(
//                INSTRUCTORS_TABLE,
//                new String[]{INSTRUCTORS_KEY_FIELD_ID, FIELD_LAST_NAME, FIELD_FIRST_NAME},
//                null,
//                null,
//                null, null, null, null);
//
//        cursor.moveToFirst();
//        return cursor;
//    }
//
//    public boolean importCoursesFromCSV(String csvFileName) {
//        AssetManager manager = mContext.getAssets();
//        InputStream inStream;
//        try {
//            inStream = manager.open(csvFileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
//        String line;
//        try {
//            while ((line = buffer.readLine()) != null) {
//                String[] fields = line.split(",");
//                if (fields.length != 4) {
//                    Log.d("OCC Course Finder", "Skipping Bad CSV Row: " + Arrays.toString(fields));
//                    continue;
//                }
//                int id = Integer.parseInt(fields[0].trim());
//                String alpha = fields[1].trim();
//                String number = fields[2].trim();
//                String title = fields[3].trim();
//                addCourse(new Course(id, alpha, number, title));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    public boolean importInstructorsFromCSV(String csvFileName) {
//        AssetManager am = mContext.getAssets();
//        InputStream inStream = null;
//        try {
//            inStream = am.open(csvFileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
//        String line;
//        try {
//            while ((line = buffer.readLine()) != null) {
//                String[] fields = line.split(",");
//                if (fields.length != 4) {
//                    Log.d("OCC Course Finder", "Skipping Bad CSV Row: " + Arrays.toString(fields));
//                    continue;
//                }
//                int id = Integer.parseInt(fields[0].trim());
//                String lastName = fields[1].trim();
//                String firstName = fields[2].trim();
//                String email = fields[3].trim();
//                addInstructor(new Instructor(id, lastName, firstName, email));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    public boolean importOfferingsFromCSV(String csvFileName) {
//        AssetManager am = mContext.getAssets();
//        InputStream inStream = null;
//        try {
//            inStream = am.open(csvFileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
//        String line;
//        try {
//            while ((line = buffer.readLine()) != null) {
//                String[] fields = line.split(",");
//                if (fields.length != 4) {
//                    Log.d("OCC Course Finder", "Skipping Bad CSV Row: " + Arrays.toString(fields));
//                    continue;
//                }
//                int crn = Integer.parseInt(fields[0].trim());
//                int semesterCode = Integer.parseInt(fields[1].trim());
//                int courseId = Integer.parseInt(fields[2].trim());
//                int instructorId = Integer.parseInt(fields[3].trim());
//                addOffering(crn, semesterCode, courseId, instructorId);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
}
