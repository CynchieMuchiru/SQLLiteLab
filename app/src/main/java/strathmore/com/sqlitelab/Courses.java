package strathmore.com.sqlitelab;

/**
 * Created by Cece on 23/10/2017.
 */

public class Courses {

    //private variables
    int courseid;
    String coursename;
    String coursefaculty;

    //Empty constructor
    public Courses(){

    }

    //constructor
    public Courses(int id, String coursename,String coursefaculty){
        this.courseid = id;
        this.coursename = coursename;
        this.coursefaculty = coursefaculty;
    }

    public Courses(String coursename, String coursefaculty){
        this.coursename = coursename;
        this.coursefaculty = coursefaculty;
    }


    //CourseID
    public int getCourseid() {
        return this.courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    //CourseName
    public String getCoursename() {
        return this.coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    //CourseFaculty
    public String getCoursefaculty() {
        return this.coursefaculty;
    }

    public void setCoursefaculty(String coursefaculty) {
        this.coursefaculty = coursefaculty;
    }
}
