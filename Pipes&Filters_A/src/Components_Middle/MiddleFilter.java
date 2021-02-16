package Components_Middle;

import java.io.IOException;

import Framework.GeneralFilter;

public class MiddleFilter extends GeneralFilter {

	@Override
	public void specificComputationForFilter() throws IOException {
		// 한 byte를 저장할 인수
		int byte_read = 0;
		// 전공이 나오는 공백 : 4
		int checkBlank = 4;
		// 공백의 개수
		int numOfBlank = 0;
		// SourceFilter에서 읽어온 byte를 저장할 buffer
		byte[] buffer = new byte[64];
		int idx = 0;
		boolean isCS = false;

		while (true) {
			while (byte_read != '\n' && byte_read != -1) {
				// student.txt를 한 줄씩 읽어오고 -1이면 더이상 읽을 내용이 없으므로 종료
				byte_read = in.read(); 
				if (byte_read == ' ') {
					// 공백의 개수를 저장
					numOfBlank++;
				}
				if (byte_read != -1) {
					// 아직 읽을 것이 남았으면 buffer 크기 늘려서 값 저장
					buffer[idx++] = (byte) byte_read;
				}
				if (numOfBlank == checkBlank && buffer[idx - 3] == 'C' && buffer[idx - 2] == 'S') { 
					// CS전공인 학생들 구분
					isCS = true;
				}
			}
			// CS전공인 학생들을 out에 써서 다음 MiddleFilter인 AddPreClass에 전달
			if (isCS == true) {
				for (int i = 0; i < idx; i++) {
					out.write((char) buffer[i]);
				}
				isCS = false;
			}
			// 다 읽으면 while문 탈출
			if (byte_read == -1) {
				System.out.println("::filtering is finished;");
				return;
			}
			// 변수 초기화
			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';
		}
	}

}
