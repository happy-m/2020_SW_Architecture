package Components.ClientInput;

/**
 * Copyright(c) 2018 All rights reserved by JU Consulting
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Common.EventPackage.EventId;

public class ClientInputMain {
	public static void main(String[] args) {
		ClientInputComponent clientInputComponent = new ClientInputComponent();

		if (clientInputComponent.registered) {
			System.out.println(
					"ClientInputMain (ID:" + clientInputComponent.getComponentId() + ") is successfully registered...");
			clientInputComponent.start();

			while (true) {
				// Menu
				System.out.println("Registeration System\n");
				System.out.println("1. List Students");
				System.out.println("2. List Courses");
				System.out.println("3. Register a new Student");
				System.out.println("4. Register a new Course");
				System.out.println("5. Delete a Student");
				System.out.println("6. Delete a Course");
				System.out.println("7. Sign up");
				System.out.println("\n 0. Quit the system");

				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				try {
					String selection = br.readLine().trim();

					if (selection.equals("1")) { // 학생 리스트 출력
						clientInputComponent.sendClientInput(EventId.ListStudents, null);

					} else if (selection.equals("2")) { // 과목 리스트 출력
						clientInputComponent.sendClientInput(EventId.ListCourses, null);

					} else if (selection.equals("3")) { // 학생 정보 추가
						String msg = "";
						boolean inputIsDone = false;
						while (!inputIsDone) { // 학생 정보 입력
							System.out.println("\nEnter student ID and press return (Ex. 20131234)>> ");
							String sSID = br.readLine().trim();
							System.out.println("\nEnter family name and press return (Ex. Gildong)>> ");
							String firstName = br.readLine().trim();
							System.out.println("\nEnter first name and press return (Ex. Hong)>> ");
							String lastName = br.readLine().trim();
							System.out.println("\nEnter department and press return (Ex. CS)>> ");
							String dep = br.readLine().trim();
							System.out.println(
									"\nEnter a list of IDs (put a space between two different IDs) of the completed courses and press return >> ");
							System.out.println("(Ex. 17651 17652 17653 17654)");
							String completedCourse = br.readLine().trim();
							msg = sSID + " " + lastName + " " + firstName + " " + dep + " " + completedCourse;

							while (true) { 
								System.out.println("\nIs it correct information? (Y/N)");
								System.out.println(msg);
								String ans = br.readLine().trim();
								if (ans.equalsIgnoreCase("y")) {
									inputIsDone = true;
									break;
								} else if (ans.equalsIgnoreCase("n")) {
									System.out.println("\nType again...");
									msg = "";
									break;
								} else {
									System.out.println("\nTyped wrong answer");
								}
							}
						}

						System.out.println("\n ** Sending an event(ID:" + EventId.RegisterStudents + ") with ");
						System.out.println("\n ** Message \"" + msg + "\" ...");
						clientInputComponent.sendClientInput(EventId.RegisterStudents, msg); 

					} else if (selection.equals("4")) { // 과목 정보 추가
						String userInput = "";
						boolean inputIsDone = false;

						while (!inputIsDone) { // 과목 정보 입력
							System.out.println("\nEnter course ID and press return (Ex. 12345)>> ");
							userInput = br.readLine().trim();
							System.out.println(
									"\nEnter the family name of the instructor and press return (Ex. Hong)>> ");
							userInput += " " + br.readLine().trim();
							System.out.println(
									"\nEnter the name of the course ( substitute a space with ab underbar(_) ) and press return (Ex. C++_Programming)>> ");
							userInput += " " + br.readLine().trim();
							System.out.println(
									"\nEnter a list of IDs (put a space between two different IDs) of prerequisite courses and press return >> ");
							System.out.println("(Ex. 12345 17651)");
							userInput += " " + br.readLine().trim();

							while (true) {
								System.out.println("\nIs it correct information? (Y/N)");
								System.out.println(userInput);
								String ans = br.readLine().trim();
								if (ans.equalsIgnoreCase("y")) {
									inputIsDone = true;
									break;
								} else if (ans.equalsIgnoreCase("n")) {
									System.out.println("\nType again...");
									userInput = "";
									break;
								} else {
									System.out.println("\nTyped wrong answer");
								}
							}
						}

						System.out.println("\nSending an event(ID:" + EventId.RegisterCourses + ") with");
						System.out.println("\n ** Message \"" + userInput + "\" ...");
						clientInputComponent.sendClientInput(EventId.RegisterCourses, userInput);

					} else if (selection.contentEquals("5")) {  // 학생 정보 삭제
						String msg = "";
						boolean inputIsDone = false;
						while (!inputIsDone) { // 삭제할 학생 ID 입력
							System.out.println("\nEnter student ID and press return (Ex. 20131234)>> ");
							String sSID = br.readLine().trim();
							msg = sSID;

							while (true) {
								System.out.println("\nIs it correct information? (Y/N)");
								System.out.println(msg);
								String ans = br.readLine().trim();
								if (ans.equalsIgnoreCase("y")) {
									inputIsDone = true;
									break;
								} else if (ans.equalsIgnoreCase("n")) {
									System.out.println("\nType again...");
									msg = "";
									break;
								} else {
									System.out.println("\nTyped wrong answer");
								}
							}
						}

						System.out.println("\n ** Sending an event(ID:" + EventId.DeleteStudents + ") with ");
						System.out.println("\n ** Message \"" + msg + "\" ...");
						clientInputComponent.sendClientInput(EventId.DeleteStudents, msg); // DeleteStudents로 msg(삭제할 학생 ID)와 이벤트 보냄

					} else if (selection.contentEquals("6")) { // 과목 정보삭제
						String userInput = "";
						boolean inputIsDone = false;

						while (!inputIsDone) { // 삭제할 과목 ID 입력
							System.out.println("\nEnter course ID and press return (Ex. 12345)>> ");
							userInput = br.readLine().trim();

							while (true) {
								System.out.println("\nIs it correct information? (Y/N)");
								System.out.println(userInput);
								String ans = br.readLine().trim();
								if (ans.equalsIgnoreCase("y")) {
									inputIsDone = true;
									break;
								} else if (ans.equalsIgnoreCase("n")) {
									System.out.println("\nType again...");
									userInput = "";
									break;
								} else {
									System.out.println("\nTyped wrong answer");
								}
							}
						}

						System.out.println("\nSending an event(ID:" + EventId.DeleteCourses + ") with");
						System.out.println("\n ** Message \"" + userInput + "\" ...");
						clientInputComponent.sendClientInput(EventId.DeleteCourses, userInput); // DeleteCourses로 userInput(삭제할 과목ID)와 이벤트 보냄

					} else if (selection.equals("7")) { // 수강신청
						String msg = "";
						String userInput = "";
						boolean inputIsDone = false;

						while (!inputIsDone) {
							System.out.println("\nEnter student ID and press return (Ex. 20131234)>> ");
							String sSID = br.readLine().trim();
							msg = sSID;

							while (true) {
								System.out.println("\nIs it correct information? (Y/N)");
								System.out.println(msg);
								String ans = br.readLine().trim();
								if (ans.equalsIgnoreCase("y")) {
									// 입력한 학생 ID가 리스트에 있는 학생이면, 과목번호 입력 / n을 누르면 다시 입력
									System.out.println("\nEnter course ID and press return (Ex. 12345)>> ");
									userInput = br.readLine().trim();
									System.out.println("\nIs it correct information? (Y/N)");
									System.out.println(userInput);
									ans = br.readLine().trim();
									if (ans.equalsIgnoreCase("y")) {
										inputIsDone = true;
										break;
									} else if (ans.equalsIgnoreCase("n")) {
										System.out.println("\nType again...");
										userInput = "";
										break;
									} else {
										System.out.println("\nTyped wrong answer");
									}

								} else if (ans.equalsIgnoreCase("n")) {
									System.out.println("\nType again...");
									msg = "";
									break;
								} else {
									System.out.println("\nTyped wrong answer");
								}
							}
						}
						String all =  msg + " " + userInput;
						System.out.println("\nSending an event(ID:" + EventId.Login + ") with");
						System.out.println("\n ** Message \"" + userInput + "\" ...");
						clientInputComponent.sendClientInput(EventId.Login, all); // 학생ID + 과목ID 전송


					} else if (selection.equals("0")) {
						System.out.println("\nSending an event(ID:" + EventId.QuitTheSystem + ")...");
						clientInputComponent.sendClientInput(EventId.QuitTheSystem, null);
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
