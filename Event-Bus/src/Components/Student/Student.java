package Components.Student;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student {

	protected String studentId;
    protected String name;
    protected String department;
    protected ArrayList completedCoursesList;
    protected int studentBalance;

    /**
     * Constructor. 
     * @param inputString
     */
    public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.name = stringTokenizer.nextToken();
    	this.name = this.name + " " + stringTokenizer.nextToken();
    	this.department = stringTokenizer.nextToken();

    	this.completedCoursesList = new ArrayList();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.completedCoursesList.add(stringTokenizer.nextToken());
    	}
    }

    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    
    public String getName() {
        return this.name;
    }

    public ArrayList getCompletedCourses() {
        return this.completedCoursesList;
    }

    public String toString() {
        String stringReturn = this.studentId + " " + this.name + " " + this.department;

        for (int i = 0; i < this.completedCoursesList.size(); i++) {
            stringReturn = stringReturn + " " + this.completedCoursesList.get(i).toString();
        }
        return stringReturn;
    }

    public int getBalance() {
		return this.studentBalance;
	}

	public void decrementBalance() {
		this.studentBalance--;
	}

}
