package Components_Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.GeneralFilter;

public class SinkFilter1 extends GeneralFilter {
	// MiddleFilter로부터 받은 파일 저장할 인수
	private String filePath;
	private int inPlace;

	public SinkFilter1(String outputFilePath, int in, int out) {
		super(in, out);
		this.inPlace = out - 1;
		this.filePath = outputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// 한byte씩 저장할 인수
		int byte_read = 0;

		try {
			FileWriter fw = new FileWriter(this.filePath);
			for (;;) {
				// MiddleFilter에서 데이터 받아온 거 읽음
				byte_read = in.get(inPlace).read();
				if (byte_read == -1) {
					fw.close();

					System.out.print("::Filtering is finished; Output file is created.");
					return;
				} else {
					fw.write(byte_read); // 파일 받아서 Output1.txt에 작성

				}
			}
		} catch (Exception e) {
			closePorts();
			e.printStackTrace();

		}
	}

}
