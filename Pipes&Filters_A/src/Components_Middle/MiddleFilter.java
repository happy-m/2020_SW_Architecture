package Components_Middle;

import java.io.IOException;

import Framework.GeneralFilter;

public class MiddleFilter extends GeneralFilter {

	@Override
	public void specificComputationForFilter() throws IOException {
		// �� byte�� ������ �μ�
		int byte_read = 0;
		// ������ ������ ���� : 4
		int checkBlank = 4;
		// ������ ����
		int numOfBlank = 0;
		// SourceFilter���� �о�� byte�� ������ buffer
		byte[] buffer = new byte[64];
		int idx = 0;
		boolean isCS = false;

		while (true) {
			while (byte_read != '\n' && byte_read != -1) {
				// student.txt�� �� �پ� �о���� -1�̸� ���̻� ���� ������ �����Ƿ� ����
				byte_read = in.read(); 
				if (byte_read == ' ') {
					// ������ ������ ����
					numOfBlank++;
				}
				if (byte_read != -1) {
					// ���� ���� ���� �������� buffer ũ�� �÷��� �� ����
					buffer[idx++] = (byte) byte_read;
				}
				if (numOfBlank == checkBlank && buffer[idx - 3] == 'C' && buffer[idx - 2] == 'S') { 
					// CS������ �л��� ����
					isCS = true;
				}
			}
			// CS������ �л����� out�� �Ἥ ���� MiddleFilter�� AddPreClass�� ����
			if (isCS == true) {
				for (int i = 0; i < idx; i++) {
					out.write((char) buffer[i]);
				}
				isCS = false;
			}
			// �� ������ while�� Ż��
			if (byte_read == -1) {
				System.out.println("::filtering is finished;");
				return;
			}
			// ���� �ʱ�ȭ
			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';
		}
	}

}
