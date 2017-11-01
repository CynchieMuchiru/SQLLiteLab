package strathmore.com.sqlitelab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         */

        //Inserting Contact
        Log.d("Insert Contact: ", "Inserting ..");
        db.addContact(new Contact("Cynthia", "9898878768900"));
        db.addContact(new Contact("Samuel", "91676887985878"));
        db.addContact(new Contact("Tommy", "788798778798"));
        db.addContact(new Contact("Christopher", "676687787980"));

        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for(Contact cn : contacts){
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();

            //Writing Contact to log
            Log.d("Contact: ", log);
        }

        /**
         * CRUD Operations
         */

        //Inserting Course
        Log.d("Insert Course: ", "Inserting ..");
        db.addCourse(new Courses("BBIT", "FIT"));
        db.addCourse(new Courses("Financial Economics", "SIMS"));
        db.addCourse(new Courses("Law", "LAW"));


        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Courses> course = db.getAllCourses();

        for(Courses cn : course){
            String log = "Id: " + cn.getCourseid() + " ,Course Name: " + cn.getCoursename() + " ,Faculty: " + cn.getCoursefaculty();

            //Writing course to log
            Log.d("Course: ", log);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
