package strathmore.com.sqlitelab;

import android.provider.BaseColumns;

/**
 * Created by Cece on 23/10/2017.
 */

public class Contract {

    public static abstract class Users implements BaseColumns{
        public static final String TABLE_CONTACTS = "contacts";

        //Contact Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_PH_NO = "phone_number";


        public static String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";

        public static String DROP_CONTACTS_TABLE = " DROP TABLE IF EXISTS " + TABLE_CONTACTS ;


    }
    public static abstract class Courses implements BaseColumns{
        public static final String TABLE_COURSES = "Courses";

        //Courses Table Column names
        public static final String KEY_COURSEID = "CourseID";
        public static final String KEY_COURSENAME = "CourseName";
        public static final String KEY_FACULTY = "Faculty";

        public static String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "("
                + KEY_COURSEID + " INTEGER PRIMARY KEY," + KEY_COURSENAME + " TEXT,"
                + KEY_FACULTY + " TEXT" + ")";

        public static String DROP_COURSES_TABLE = "DROP TABLE IF EXISTS " + TABLE_COURSES ;

    }


}
