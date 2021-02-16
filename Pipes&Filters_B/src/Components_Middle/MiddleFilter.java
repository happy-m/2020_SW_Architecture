package Components_Middle;
import java.io.IOException;

import Framework.GeneralFilter;

public class MiddleFilter extends GeneralFilter {

	public MiddleFilter(int inPlace, int outPlace) {
		super(inPlace, outPlace);
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// �� byte�� ������ �μ�
		int byte_read = 0;
		// ����
		int i = 0;
		int idx = 0;
		// ���� ������ ������ buffer
		byte[] buffer = new byte[2056];
		// Students.txt�� �����ϴ� stringBuffer
		StringBuffer stu = new StringBuffer();
		// Courses.txt�� �����ϴ� stringBuffer
		StringBuffer cour = new StringBuffer();
		// Students.txt, Courses.txt ��� �����ϴ� stringBuffer
		StringBuffer all = new StringBuffer();
		// ���������� �� ���� �л����� �����ϴ� stringBuffer
		StringBuffer stuOk = new StringBuffer();
		// ���������� �� ���� ���� �л����� �����ϴ� stringBuffer
		StringBuffer stuNo = new StringBuffer();

		while (true) {
			while (i < 2) { // ���� ���� �޾Ƽ� ���� �Ŀ� stringBuffer�� ����
				byte_read = in.get(i).read();
				if (byte_read != -1) {
					buffer[idx] = (byte) byte_read;
					all.append((char) buffer[idx]);
					idx++;
				}

				if (byte_read == -1) {

					if (i == 0) { // Students.txt�� �а� stringBuffer�� ����
						stu.append(all);
						System.out.println("::Student filtering is finished;");
					}
					if (i == 1) { // Students.txt, Courses.txt�� �а� stringBuffer�� ����
						cour.append(all);
						cour.delete(0, 910); // Students.txt�κ� ���� > Courses.txt�κи� ����
						System.out.println("::Course filtering is finished");
					}
					i++;
				}
			}

			String cour2 = cour.toString();
			// �ٴ����� ���ڿ� �б�
			String[] courLine = cour2.split(System.getProperty("line.separator"));

			String stu2 = stu.toString();
			String[] stuLine = stu2.split(System.getProperty("line.separator"));

			String c1 = "";
			String c2 = "";
			String c3 = "";
			String c4 = "";

			// 17651�� �������� 12345 ����
			for (int k = 0; k < courLine.length; k++) {
				if (courLine[k].contains("17651") && !courLine[k].contains("17655") && !courLine[k].contains("17652")
						&& !courLine[k].contains("17654")) {
					c1 = courLine[k];
					c1 = c1.substring(c1.length() - 6, c1.length() - 1);
				}
			}

			// 17652�� �������� 23456 ����
			for (int k = 1; k < courLine.length; k++) {
				if (courLine[k].contains("17652") && !courLine[k].contains("17655") && !courLine[k].contains("17651")
						&& !courLine[k].contains("17654")) {
					c2 = courLine[k];
					c2 = c2.substring(c2.length() - 6, c2.length() - 1);
				}
			}

			// 17655�� �������� 12345, 17651 ����
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
					if (stuLine[l].contains(c4) && stuLine[l].contains(c3)) { // 17655 12345 17651 ���� �л��� ���
						stuOk.append(stuLine[l] + "\n\r"); // stuOk : ���������� ��� �� ���� �л� ����
					} else { // 17655�� ������� ���������� �ϳ��� �� ���� �л��� ���
						stuNo.append(stuLine[l] + "\n\r"); // stuNo : ���������� ��� ���� ���� �л� ����
					}
				} else if (stuLine[l].contains("17652")) {
					if (stuLine[l].contains(c2)) { // 17652 23456 ���� �л��� ���
						stuOk.append(stuLine[l] + "\n\r");
					} else { // �������� 23456 �� ���� �л��� ���
						stuNo.append(stuLine[l] + "\n\r");
					}
				} else if (stuLine[l].contains("17654") && stuLine[l].contains(c3)) { // 17654 17651 ���� �л�
					if (stuLine[l].contains("12345")) { // 17654 17651 12345 ���� �л��� ���
						stuOk.append(stuLine[l] + "\n\r");
					} else { // 17654 17651�� ���� �л��� ���(�������� 12345�� �� ���� ���)
						stuNo.append(stuLine[l] + "\n\r");
					}
				} else if (stuLine[l].contains("17651")) {
					if (stuLine[l].contains(c1)) { // 17651 12345 ���� �л��� ���
						stuOk.append(stuLine[l] + "\n\r");
					} else { // �������� 12345 �� ���� �л��� ���
						stuNo.append(stuLine[l] + "\n\r");
					}
				} else if (stuLine[l].contains("12345") && stuLine[l].contains("23456") // ����������� ���� ���� �л��� ���
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
			// ���� ������ ���� SinkFilter�� ������

			if (i == 2) {
				i = 0;
				return;
			}
			// ���� �ʱ�ȭ
			idx = 0;
			byte_read = '\0';

		}
	}

}
