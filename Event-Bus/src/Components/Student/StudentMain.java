package Components.Student;

/**
 * Copyright(c) 2018 All rights reserved by JU Consulting
 */

import Common.EventPackage.Event;
import Common.EventPackage.EventBusUtil;
import Common.EventPackage.EventId;
import Common.EventPackage.EventQueue;

public class StudentMain {
	public static void main(String args[]) {
		EventBusUtil eventBusInterface = new EventBusUtil();
		Event event = null;
		EventQueue eventQueue = null;
		boolean done = false;
		StudentComponent studentsList = null;

		if (eventBusInterface.getComponentId() != -1) {
			System.out.println(
					"StudentMain (ID:" + eventBusInterface.getComponentId() + ") is successfully registered...");
		} else {
			System.out.println("StudentMain is failed to register. Restart this component.");
		}

		try {
			studentsList = new StudentComponent("Students.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (!done) {

			eventQueue = eventBusInterface.getEventQueue();

			for (int i = 0; i < eventQueue.getSize(); i++) {
				event = eventQueue.getEvent();
				System.out.println("Received an event(ID: " + event.getEventId() + ")...");

				if (event.getEventId() == EventId.ListStudents) {
					String returnString = "";
					for (int j = 0; j < studentsList.vStudent.size(); j++) {
						returnString += (j == 0 ? "" : "\n") + ((Student) studentsList.vStudent.get(j)).toString();
					}

					System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
					System.out.println("\n ** Message: \n" + returnString + "\n  ...");
					eventBusInterface.sendEvent(new Event(EventId.ClientOutput, returnString));

				} else if (event.getEventId() == EventId.RegisterStudents) {
					String studentInfo = event.getMessage();
					System.out.println("Not null");
					Student student = new Student(studentInfo);
					if (studentsList.isRegisteredStudent(student.studentId) == false) {
						studentsList.vStudent.add(new Student(studentInfo));
						System.out.println("A new student is successfully added...");
						System.out.println("\"" + studentInfo + "\"");
					} else {
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: This student is already registered.  ...");
						eventBusInterface
								.sendEvent(new Event(EventId.ClientOutput, "This student is already registered."));
					}

				} else if (event.getEventId() == EventId.DeleteStudents) {
					String studentInfo = event.getMessage(); // 삭제할 학생 ID
					System.out.println("Not null");
					if (studentsList.isRegisteredStudent(studentInfo) == true) { // 삭제하고자 하는 학생 ID가 학생 리스트에 있으면
						studentsList.vStudent.remove(studentsList.getStudentIndex(studentInfo)); // 해당학생이 속하는 인덱스를 찾아 정보 삭제
						System.out.println("A student is successfully deleted...");
						System.out.println("\"" + studentInfo + "\"");
						eventBusInterface
						.sendEvent(new Event(EventId.ClientOutput, "A student is successfully deleted..."));
					} else {
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: This student is already deleted.  ...");
						eventBusInterface
								.sendEvent(new Event(EventId.ClientOutput, "This student is already deleted."));
					}

				} else if (event.getEventId() == EventId.Login) {
					String studentInfo = event.getMessage(); // 학생 + 과목
					if (studentsList.isRegisteredStudent(studentInfo.substring(0, 8)) == true) { // 학생정보 있으면 로그인 성공
						System.out.println("A student is successfully login...");
						System.out.println("\"" + studentInfo + "\"");
						String student = studentsList.readStudent(studentInfo.substring(0, 8)); // 학생 정보
						String courseInfo = studentInfo.substring(studentInfo.length() - 5, studentInfo.length()); // 과목ID

						if (!student.matches(studentInfo.substring(9))) { // 학생이 이 과목을 안 들었으면
							System.out.println("\n ** Message: loading.  ...");
							eventBusInterface.sendEvent(new Event(EventId.Registeration, studentInfo)); // 학생 + 과목

						} else { // 들은 과목이면 초기화면으로
							System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
							System.out.println("\n ** Message: This student has already taken this course.  ...");
							eventBusInterface.sendEvent(
									new Event(EventId.ClientOutput, "This student has already taken this course."));
						}

					} else { // 학생 정보가 존재하지 않으면
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: This student is not exist.  ...");
						eventBusInterface.sendEvent(new Event(EventId.ClientOutput, "This student is not exist."));
					}

				} else if (event.getEventId() == EventId.SuccessRegistration) { // 과목이 존재하고 학생이 듣지 않은 과목이면
					String studentInfo = event.getMessage(); // 추가하고자 하는 과목
					String student = studentsList.readStudent(studentInfo.substring(0, 8));
					boolean isSuccess = false;
					// 선수과목 들었는지 확인
					if (studentInfo.substring(9).equals("17651")) {
						if (student.contains("12345")) { // 선수과목 12345 들었으면
							student = student + " " + studentInfo.substring(9); // 추가
							studentsList.vStudent.remove(studentsList.getStudentIndex(studentInfo.substring(0, 8)));
							studentsList.vStudent.add(new Student(student));
							isSuccess = true;
						} else {
							System.out.println("Please take the required courses. ...");
							eventBusInterface.sendEvent(new Event(EventId.ClientOutput,
									"Please take the required courses. ..."));
						}
					} else if (studentInfo.substring(9).equals("17652")) {
						if (student.contains("23456")) { // 선수과목 23456 들었으면
							student = student + " " + studentInfo.substring(9); // 추가
							studentsList.vStudent.remove(studentsList.getStudentIndex(studentInfo.substring(0, 8)));
							studentsList.vStudent.add(new Student(student));
							isSuccess = true;
						} else {
							System.out.println("Please take the required courses. ...");
							eventBusInterface.sendEvent(new Event(EventId.ClientOutput,
									"Please take the required courses. ..."));
						}
					} else if (studentInfo.substring(9).equals("17654")) {
						if (student.contains("17651") && student.contains("12345")) { // 선수과목 17651 12345 들었으면
							student = student + " " + studentInfo.substring(9); // 추가
							studentsList.vStudent.remove(studentsList.getStudentIndex(studentInfo.substring(0, 8)));
							studentsList.vStudent.add(new Student(student));
							isSuccess = true;
						} else {
							System.out.println("Please take the required courses. ...");
							eventBusInterface.sendEvent(new Event(EventId.ClientOutput,
									"Please take the required courses. ..."));
						}
					} else if (studentInfo.substring(9).contains("17655")) {
						if (student.contains("17651") && student.contains("12345")) { // 선수과목 12345 17651 들었으면
							student = student + " " + studentInfo.substring(9); // 추가
							studentsList.vStudent.remove(studentsList.getStudentIndex(studentInfo.substring(0, 8)));
							studentsList.vStudent.add(new Student(student));
							isSuccess = true;
						} else {
							System.out.println("Please take the required courses. ...");
							eventBusInterface.sendEvent(new Event(EventId.ClientOutput,
									"Please take the required courses. ..."));
						}
					} else if (studentInfo.substring(9).equals("12345") || studentInfo.substring(9).equals("23456") 
							|| studentInfo.substring(9).equals("17653")) { // 선수과목 없는 과목을 들었으면
						student = student + " " + studentInfo.substring(9); // 추가
						studentsList.vStudent.remove(studentsList.getStudentIndex(studentInfo.substring(0, 8)));
						studentsList.vStudent.add(new Student(student));
						isSuccess = true;
					}
					if (isSuccess == true) {
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: Success!  ..." + student);
						eventBusInterface.sendEvent(
								new Event(EventId.ClientOutput, "Success!!\n\r" + student));
					}	
				} else if (event.getEventId() == EventId.		QuitTheSystem) {
					eventBusInterface.unRegister();
					done = true;
				}
			}

		}
		System.out.println("Shut down the component....");
	}
}
