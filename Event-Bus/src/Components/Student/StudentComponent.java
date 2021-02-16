package Components.Student;

/**
 * Copyright(c) 2018 All rights reserved by JU Consulting
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentComponent {
	protected ArrayList vStudent;

	public StudentComponent(String sStudentFileName) throws FileNotFoundException, IOException {

		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));

		this.vStudent = new ArrayList();

		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.vStudent.add(new Student(stuInfo));
			}
		}

		objStudentFile.close();
	}
	
	public ArrayList getAllStudentRecords() {
		return this.vStudent;
	}
	
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student objStudent = (Student) this.vStudent.get(i);
			if (objStudent.match(sSID)) {
				return true;
			}
		}
		return false;
	}
	
	public String readStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student objStudent = (Student) this.vStudent.get(i);
			if (objStudent.match(sSID)) {
				 String obj = this.vStudent.get(i).toString();
				return obj;
			}
		}
		return null;
	}
		 
	public int getStudentIndex(String sSID) {
        for (int i = 0; i < this.vStudent.size(); i++) {
            Student objStudent = (Student) this.vStudent.get(i);
            if (objStudent.match(sSID)) {
                return i;
            }
        }
        return -1;
    }
}
