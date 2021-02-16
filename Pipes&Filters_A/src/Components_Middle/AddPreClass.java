package Components_Middle;

import java.io.IOException;

import Framework.GeneralFilter;

public class AddPreClass extends GeneralFilter {

	public void specificComputationForFilter() throws IOException {
		// �� byte ���� �μ�
		int byte_read = 0;
		int idx = 0;
		// MiddleFilter���� �о�� byte�� ������ buffer
		byte[] buffer = new byte[1000];
		// �о�� byte�߿� �л� ������ �����ϴ� stringBuffer
		StringBuffer stu = new StringBuffer();
		// �ʼ�����(12345, 23456, 17651)�� �߰��ؼ� �����ϴ� stringBuffer
		StringBuffer plus = new StringBuffer();

		while (byte_read != -1) { // course���� �о stringBuffer�� ����
			byte_read = in.read();
			buffer[idx] = (byte) byte_read;
			stu.append((char) buffer[idx]);
			idx++;
		}
		String stu2 = stu.toString();
		// �ٴ����� ���ڿ� �б�
		String[] stuLine = stu2.split(System.getProperty("line.separator"));

		for (int i = 0; i < stuLine.length; i++) {
			if (!stuLine[i].contains("12345") || !stuLine[i].contains("23456")) {
				if (!stuLine[i].contains("12345") && !stuLine[i].contains("23456")) {
					// 12345, 23456 ��� ���� ���� �л��� ��� �� ���� �߰�
					plus.append(stuLine[i] + " 12345" + " 23456" + "\n\r");
				} else if (!stuLine[i].contains("12345")) {
					// 12345�� ���� ���� �л��� ��� 12345 �߰�
					plus.append(stuLine[i] + " 12345" + "\n\r");
				} else if (!stuLine[i].contains("23456")) {
					if (!stuLine[i].contains("17651")) {
						// 23456�� 17651�� ���� ���� �л��� ��� �� ���� �߰�
						plus.append(stuLine[i] + " 23456" + " 17651" + "\n\r");
					} else {
						// 17651�� ���, 23456�� ���� ���� �л��� ��� 23456 �߰�
						plus.append(stuLine[i] + " 23456" + "\n\r");
					}
				}
			} else if (!stuLine[i].contains("17651")) {
				// 17651 �� ���� ���� �л��� ���
				plus.append(stuLine[i] + " 17651" + "\n\r");
			}
			if (stuLine[i].contains("12345") && stuLine[i].contains("23456") && stuLine[i].contains("17651")) {
				// ��� �ʼ� ������ �� ���� �л�(�߰����� �ʾƵ� �Ǵ� �л�)�� ��� �״�� �����´�.
				plus.append(stuLine[i] + "\n\r");
			}

		}
		String plus2 = new String();
		plus2 = plus.substring(0, 471) + plus.substring(472, 478);
		String stuFinal = plus2.toString();
		out.write(stuFinal.getBytes());
		// ���� �߰��ؼ� ������ ����� string���� �����ͼ� �������� �� �ۼ�
	}

}