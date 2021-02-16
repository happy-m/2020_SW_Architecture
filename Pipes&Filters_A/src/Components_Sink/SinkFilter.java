package Components_Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.GeneralFilter;

public class SinkFilter extends GeneralFilter {
	private String filePath;
	
	// Students.txt data를 읽어서 filePath에 저장
	public SinkFilter(String outputFilePath) {
		this.filePath = outputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// 한 byte 저장 인수
		int byte_read = 0;

		try {
			// file 생성
			FileWriter fw = new FileWriter(this.filePath);
			for (;;) {
				byte_read = in.read();
				if (byte_read == -1) { // 다 썼으면 종료
					fw.close();
					System.out.print("::Filtering is finished; Output file is created.");
					return;
				} else { // 쓸 게 남았으면 file에 작성
					fw.write(byte_read);
				}
			}
		} catch (Exception e) {
			closePorts();
			e.printStackTrace();

		}
	}

}
