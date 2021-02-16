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

				} else if (event.getEventId() == EventId.DeleteCourses) { // ���� ���� ���� 
					String courseInfo = event.getMessage();
					if (coursesList.isRegisteredCourse(courseInfo) == true) { // �����ϰ��� �ϴ� ����ID�� ���񸮽�Ʈ�� ������ 
						coursesList.vCourse.remove(coursesList.getCourseIndex(courseInfo)); // �ش� ���� �ε��� �����ͼ� ����
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
					String courseInfo = event.getMessage(); // �л� + ���� ����
					String courseCode = courseInfo.substring(courseInfo.length()-5,courseInfo.length()); // ����ID
					if (coursesList.isRegisteredCourse(courseCode) == true) { // �ִ� �����̸�
						System.out.println("\n ** Message: Please, wait.  ...");
						eventBusInterface.sendEvent(
								new Event(EventId.SuccessRegistration , courseInfo)); // �л� + ���� ����
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
