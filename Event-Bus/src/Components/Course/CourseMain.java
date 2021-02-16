package Components.Course;

import java.io.FileNotFoundException;
import java.io.IOException;

import Common.EventPackage.Event;
import Common.EventPackage.EventBusUtil;
import Common.EventPackage.EventId;
import Common.EventPackage.EventQueue;

public class CourseMain {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		EventBusUtil eventBusInterface = new EventBusUtil();
		Event event = null;
		EventQueue eventQueue = null;
		boolean done = false;
		CourseComponent coursesList = null;

		if (eventBusInterface.getComponentId() != -1) {
			System.out.println(
					"CourseMain (ID:" + eventBusInterface.getComponentId() + ") is successfully registered...");
		} else {
			System.out.println("CourseMain is failed to register. Restart this component.");
		}

		try {
			coursesList = new CourseComponent("Courses.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (!done) {
			eventQueue = eventBusInterface.getEventQueue();

			for (int i = 0; i < eventQueue.getSize(); i++) {
				event = eventQueue.getEvent();
				System.out.println("Received an event(ID: " + event.getEventId() + ")...");

				if (event.getEventId() == EventId.ListCourses) {
					String returnString = "";
					for (int j = 0; j < coursesList.vCourse.size(); j++) {
						returnString += ((Course) coursesList.vCourse.get(j)).toString() + "\n";
					}

					System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
					System.out.println("\n ** Message: \n" + returnString + "\n  ...");
					eventBusInterface.sendEvent(new Event(EventId.ClientOutput, returnString));

				} else if (event.getEventId() == EventId.RegisterCourses) {
					String courseInfo = event.getMessage();
					Course course = new Course(courseInfo);
					if (coursesList.isRegisteredCourse(course.courseId) == false) {
						coursesList.vCourse.add(new Course(courseInfo));
						System.out.println("A new course is successfully added...");
						System.out.println("\"" + courseInfo + "\"");
						
					} else {
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: This course is already registered.  ...");
						eventBusInterface
								.sendEvent(new Event(EventId.ClientOutput, "This course is already registered."));
					}

				} else if (event.getEventId() == EventId.DeleteCourses) { // 과목 정보 삭제 
					String courseInfo = event.getMessage();
					if (coursesList.isRegisteredCourse(courseInfo) == true) { // 삭제하고자 하는 과목ID가 과목리스트에 있으면 
						coursesList.vCourse.remove(coursesList.getCourseIndex(courseInfo)); // 해당 과목 인덱스 가져와서 삭제
						System.out.println("A course is successfully deleted...");
						System.out.println("\"" + courseInfo + "\"");
						eventBusInterface
						.sendEvent(new Event(EventId.ClientOutput, "A course is successfully deleted..."));
					} else {
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: This course is already deleted.  ...");
						eventBusInterface.sendEvent(
								new Event(EventId.ClientOutput, "This course is already deleted."));
					}

				} else if (event.getEventId() == EventId.Registeration) {
					String courseInfo = event.getMessage(); // 학생 + 과목 정보
					String courseCode = courseInfo.substring(courseInfo.length()-5,courseInfo.length()); // 과목ID
					if (coursesList.isRegisteredCourse(courseCode) == true) { // 있는 과목이면
						System.out.println("\n ** Message: Please, wait.  ...");
						eventBusInterface.sendEvent(
								new Event(EventId.SuccessRegistration , courseInfo)); // 학생 + 과목 정보
					} else {
						System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
						System.out.println("\n ** Message: This course is not exist.  ...");
						eventBusInterface.sendEvent(new Event(EventId.ClientOutput, "This course is not exist."));
					}
				} else if (event.getEventId() == EventId.QuitTheSystem) {
					eventBusInterface.unRegister();
					done = true;
				}
			}
		}
		System.out.println("Shut down the component....");
	}
}
