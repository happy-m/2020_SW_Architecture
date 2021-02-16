package Components_Middle;
import java.io.IOException;

import Framework.GeneralFilter;

public class MiddleFilter extends GeneralFilter {

	public MiddleFilter(int inPlace, int outPlace) {
		super(inPlace, outPlace);
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// 한 byte씩 저장할 인수
		int byte_read = 0;
		// 번지
		int i = 0;
		int idx = 0;
		// 읽은 파일을 저장할 buffer
		byte[] buffer = new byte[2056];
		// Students.txt만 저장하는 stringBuffer
		StringBuffer stu = new StringBuffer();
		// Courses.txt만 저장하는 stringBuffer
		StringBuffer cour = new StringBuffer();
		// Students.txt, Courses.txt 모두 저장하는 stringBuffer
		StringBuffer all = new StringBuffer();
		// 선수과목을 잘 들은 학생들을 저장하는 stringBuffer
		StringBuffer stuOk = new StringBuffer();
		// 선수과목을 잘 듣지 않은 학생들을 저장하는 stringBuffer
		StringBuffer stuNo = new StringBuffer();

		while (true) {
			while (i < 2) { // 파일 전달 받아서 읽은 후에 stringBuffer에 저장
				byte_read = in.get(i).read();
				if (byte_read != -1) {
					buffer[idx] = (byte) byte_read;
					all.append((char) buffer[idx]);
					idx++;
				}

				if (byte_read == -1) {

					if (i == 0) { // Students.txt를 읽고 stringBuffer에 저장
						stu.append(all);
						System.out.println("::Student filtering is finished;");
					}
					if (i == 1) { // Students.txt, Courses.txt를 읽고 stringBuffer에 저장
						cour.append(all);
						cour.delete(0, 910); // Students.txt부분 삭제 > Courses.txt부분만 저장
						System.out.println("::Course filtering is finished");
					}
					i++;
				}
			}

			String cour2 = cour.toString();
			// 줄단위로 문자열 읽기
			String[] courLine = cour2.split(System.getProperty("line.separator"));

			String stu2 = stu.toString();
			String[] stuLine = stu2.split(System.getProperty("line.separator"));

			String c1 = "";
			String c2 = "";
			String c3 = "";
			String c4 = "";

			// 17651의 선수과목 12345 저장
			for (int k = 0; k < courLine.length; k++) {
				if (courLine[k].contains("17651") && !courLine[k].contains("17655") && !courLine[k].contains("17652")
						&& !courLine[k].contains("17654")) {
					c1 = courLine[k];
					c1 = c1.substring(c1.length() - 6, c1.length() - 1);
				}
			}

			// 17652의 선수과목 23456 저장
			for (int k = 1; k < courLine.length; k++) {
				if (courLine[k].contains("17652") && !courLine[k].contains("17655") && !courLine[k].contains("17651")
						&& !courLine[k].contains("17654")) {
					c2 = courLine[k];
					c2 = c2.substring(c2.length() - 6, c2.length() - 1);
				}
			}

			// 17655의 선수과목 12345, 17651 저장
			for (int k = 0; k < courLine.length; k++) {
				if (courLine[k].contains("17655") && courLine[k].contains("17651") && !courLine[k].contains("17652")
						&& !courLine[k].contains("17654")) {
					c3 = courLine[k];
					c3 = c3.substring(c3.length() - 12, c3.length());
					c4 = c3.substring(c3.length() - 11, c3.length() - 6); // 12345
					c3 = c3.substring(c3.length() - 5, c3.length()); // 17651

				}
			}

			for (int l = 0; l < stuLine.length; l++) {
				if (stuLine[l].contains("17655")) {
					if (stuLine[l].contains(c4) && stuLine[l].contains(c3)) { // 17655 12345 17651 들은 학생일 경우
						stuOk.append(stuLine[l] + "\n\r"); // stuOk : 선수과목을 모두 잘 들은 학생 저장
					} else { // 17655는 들었으나 선수과목을 하나라도 안 들은 학생일 경우
						stuNo.append(stuLine[l] + "\n\r"); // stuNo : 선수과목을 모두 듣지 않은 학생 저장
					}
				} else if (stuLine[l].contains("17652")) {
					if (stuLine[l].contains(c2)) { // 17652 23456 들은 학생일 경우
						stuOk.append(stuLine[l] + "\n\r");
					} else { // 선수과목 23456 안 들은 학생일 경우
						stuNo.append(stuLine[l] + "\n\r");
					}
				} else if (stuLine[l].contains("17654") && stuLine[l].contains(c3)) { // 17654 17651 들은 학생
					if (stuLine[l].contains("12345")) { // 17654 17651 12345 들은 학생일 경우
						stuOk.append(stuLine[l] + "\n\r");
					} else { // 17654 17651만 들은 학생일 경우(선수과목 12345를 안 들은 경우)
						stuNo.append(stuLine[l] + "\n\r");
					}
				} else if (stuLine[l].contains("17651")) {
					if (stuLine[l].contains(c1)) { // 17651 12345 들은 학생일 경우
						stuOk.append(stuLine[l] + "\n\r");
					} else { // 선수과목 12345 안 들은 학생일 경우
						stuNo.append(stuLine[l] + "\n\r");
					}
				} else if (stuLine[l].contains("12345") && stuLine[l].contains("23456") // 선수과목없는 과목만 들은 학생일 경우
						&& stuLine[l].contains("17653")) {
					if (!stuLine[l].contains("17655") && !stuLine[l].contains("17654") && !stuLine[l].contains("17652")
							&& !stuLine[l].contains("17651")) {
						stuOk.append(stuLine[l] + "\n\r");
					}
				} 
			}
			String finalNo = stuNo.toString();
			out.get(1).write(finalNo.getBytes());
			String finalOk = stuOk.toString();
			out.get(0).write(finalOk.getBytes());
			// 각각 저장한 파일 SinkFilter로 보내기

			if (i == 2) {
				i = 0;
				return;
			}
			// 변수 초기화
			idx = 0;
			byte_read = '\0';

		}
	}

}
