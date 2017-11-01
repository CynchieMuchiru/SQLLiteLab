package strathmore.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static strathmore.com.sqlitelab.Contract.Courses.KEY_COURSEID;
import static strathmore.com.sqlitelab.Contract.Courses.KEY_COURSENAME;
import static strathmore.com.sqlitelab.Contract.Courses.KEY_FACULTY;
import static strathmore.com.sqlitelab.Contract.Courses.TABLE_COURSES;

/**
 * Created by Cece on 17/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //All Static variables
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "contactsManager";



    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";*/
        db.execSQL(Contract.Users.CREATE_CONTACTS_TABLE);
        db.execSQL(Contract.Courses.CREATE_COURSES_TABLE);

    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if it existed
        db.execSQL(Contract.Users.DROP_CONTACTS_TABLE);
        db.execSQL(Contract.Courses.DROP_COURSES_TABLE);

        //Create table again
        onCreate(db);
    }

    //adding a new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.Users.KEY_NAME, contact.getName());//Contact Name
        values.put(Contract.Users.KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number

        //Inserting Row
        db.insert(Contract.Users.TABLE_CONTACTS, null, values);
        db.close();//Closing database connection

    }

    //adding a new course
    public void addCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues courses = new ContentValues();
        courses.put(KEY_COURSENAME, course.getCoursename());//Contact Name
        courses.put(KEY_FACULTY, course.getCoursefaculty()); //Contact Phone Number

        //Inserting Row
        db.insert(TABLE_COURSES, null, courses);
        db.close();//Closing database connection

    }

    //Getting single contact
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Contract.Users.TABLE_CONTACTS, new String[] { Contract.Users.KEY_ID,
                Contract.Users.KEY_NAME, Contract.Users.KEY_PH_NO }, Contract.Users.KEY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return contact;
    }

    //Getting single course
    public Courses getCourse(int id){
        SQLiteDatabase db2 = this.getReadableDatabase();

        Cursor cursor2 = db2.query(TABLE_COURSES, new String[] { KEY_COURSEID,
                        KEY_COURSENAME, KEY_FACULTY }, KEY_COURSEID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor2 != null)
            cursor2.moveToFirst();

        Courses course = new Courses(Integer.parseInt(cursor2.getString(0)),
                cursor2.getString(1), cursor2.getString(2));

        //return contact
        return course;
    }

    //Getting All Contact
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();

        //Select ALL Query
        String selectQuery = "SELECT  * FROM " + Contract.Users.TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));// 0 is the index of the column id
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());


        }
        return contactList;
    }

        //Getting All Courses
        public List<Courses> getAllCourses(){
            List<Courses> courseList = new ArrayList<Courses>();

            //Select ALL Query
            String selectCourseQuery = "SELECT  * FROM " + TABLE_COURSES;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectCourseQuery, null);

            //looping through all rows and adding to list
            if(cursor.moveToFirst()){
                do{
                    Courses course = new Courses();
                    course.setCourseid(Integer.parseInt(cursor.getString(0)));// 0 is the index of the column id
                    course.setCoursename(cursor.getString(1));
                    course.setCoursefaculty(cursor.getString(2));
                    //Adding course to list
                    courseList.add(course);
                } while (cursor.moveToNext());
            }

        //return course list
        return courseList;

    }

    //Getting contacts Count
    public int getContactsCount(){
        String countQuery = "SELECT  * FROM " + Contract.Users.TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //Getting course Count
    public int getCourseCount(){
        String countCourseQuery = "SELECT  * FROM " + TABLE_COURSES;
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery(countCourseQuery, null);
        cursor2.close();

        //return count
        return cursor2.getCount();
    }

    //Updating single contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.Users.KEY_NAME, contact.getName());
        values.put(Contract.Users.KEY_PH_NO, contact.getPhoneNumber());

        //updating row
        return db.update(Contract.Users.TABLE_CONTACTS, values, Contract.Users.KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });

    }

    //Updating single Course
    public int updateCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSENAME, course.getCoursename());
        values.put(KEY_FACULTY, course.getCoursefaculty());

        //updating row
        return db.update(TABLE_COURSES, values, KEY_COURSEID + " = ?",
                new String[] { String.valueOf(course.getCourseid()) });

    }

    //Deleting a single contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contract.Users.TABLE_CONTACTS, Contract.Users.KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    //Deleting a single course
    public void deleteCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSES, KEY_COURSEID + " = ?",
                new String[] { String.valueOf(course.getCourseid()) });
        db.close();
    }


}
