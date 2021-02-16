package Components_Middle;

import java.io.IOException;

import Framework.GeneralFilter;

public class AddPreClass extends GeneralFilter {

	public void specificComputationForFilter() throws IOException {
		// 한 byte 저장 인수
		int byte_read = 0;
		int idx = 0;
		// MiddleFilter에서 읽어온 byte를 저장할 buffer
		byte[] buffer = new byte[1000];
		// 읽어온 byte중에 학생 정보만 저장하는 stringBuffer
		StringBuffer stu = new StringBuffer();
		// 필수과목(12345, 23456, 17651)을 추가해서 저장하는 stringBuffer
		StringBuffer plus = new StringBuffer();

		while (byte_read != -1) { // course파일 읽어서 stringBuffer에 저장
			byte_read = in.read();
			buffer[idx] = (byte) byte_read;
			stu.append((char) buffer[idx]);
			idx++;
		}
		String stu2 = stu.toString();
		// 줄단위로 문자열 읽기
		String[] stuLine = stu2.split(System.getProperty("line.separator"));

		for (int i = 0; i < stuLine.length; i++) {
			if (!stuLine[i].contains("12345") || !stuLine[i].contains("23456")) {
				if (!stuLine[i].contains("12345") && !stuLine[i].contains("23456")) {
					// 12345, 23456 모두 듣지 않은 학생일 경우 두 과목 추가
					plus.append(stuLine[i] + " 12345" + " 23456" + "\n\r");
				} else if (!stuLine[i].contains("12345")) {
					// 12345를 듣지 않은 학생일 경우 12345 추가
					plus.append(stuLine[i] + " 12345" + "\n\r");
				} else if (!stuLine[i].contains("23456")) {
					if (!stuLine[i].contains("17651")) {
						// 23456와 17651을 듣지 않은 학생일 경우 두 과목 추가
						plus.append(stuLine[i] + " 23456" + " 17651" + "\n\r");
					} else {
						// 17651은 듣고, 23456을 듣지 않은 학생일 경우 23456 추가
						plus.append(stuLine[i] + " 23456" + "\n\r");
					}
				}
			} else if (!stuLine[i].contains("17651")) {
				// 17651 을 듣지 않은 학생일 경우
				plus.append(stuLine[i] + " 17651" + "\n\r");
			}
			if (stuLine[i].contains("12345") && stuLine[i].contains("23456") && stuLine[i].contains("17651")) {
				// 모든 필수 과목을 다 들은 학생(추가하지 않아도 되는 학생)일 경우 그대로 가져온다.
				plus.append(stuLine[i] + "\n\r");
			}

		}
		String plus2 = new String();
		plus2 = plus.substring(0, 471) + plus.substring(472, 478);
		String stuFinal = plus2.toString();
		out.write(stuFinal.getBytes());
		// 과목 추가해서 정리한 목록을 string으로 가져와서 정리해준 후 작성
	}

}