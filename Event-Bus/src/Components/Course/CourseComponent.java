package Components.Course;
/**
 * Copyright(c) 2018 All rights reserved by JU Consulting
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseComponent {
    protected ArrayList vCourse;
    protected ArrayList rCourse;
  
    public CourseComponent(String sCourseFileName) throws FileNotFoundException, IOException {
        BufferedReader objCourseFile  = new BufferedReader(new FileReader(sCourseFileName));
        
        this.vCourse  = new ArrayList();
        
        while (objCourseFile.ready()) {
            String courseInfo = objCourseFile.readLine();
            if(!courseInfo.equals("")) {
                this.vCourse.add(new Course(courseInfo));
            }
        }
        
        objCourseFile.close();
    }

    public ArrayList getAllCourseRecords() {
        return this.vCourse;
    }
 
    public boolean isRegisteredCourse(String courseId) {
        for (int i = 0; i < this.vCourse.size(); i++) {
            Course course = (Course) this.vCourse.get(i);
            if(course.match(courseId)) {
                return true;
            }
        }
        return false;
    }
    
    public Course readCourse(String courseId) {
        for (int i = 0; i < this.vCourse.size(); i++) {
            Course course = (Course) this.vCourse.get(i);
            if(course.match(courseId)) {
                return course;
            }
        }
        return null;
    }
    
    public int getCourseIndex(String courseId) {
        for (int i = 0; i < this.vCourse.size(); i++) {
            Course course = (Course) this.vCourse.get(i);
            if(course.match(courseId)) {
                return i;
            }
        }
        return -1;
    }
}
