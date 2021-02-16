package Components_Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.GeneralFilter;

public class SinkFilter1 extends GeneralFilter {
	// MiddleFilter�κ��� ���� ���� ������ �μ�
	private String filePath;
	private int inPlace;

	public SinkFilter1(String outputFilePath, int in, int out) {
		super(in, out);
		this.inPlace = out - 1;
		this.filePath = outputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// ��byte�� ������ �μ�
		int byte_read = 0;

		try {
			FileWriter fw = new FileWriter(this.filePath);
			for (;;) {
				// MiddleFilter���� ������ �޾ƿ� �� ����
				byte_read = in.get(inPlace).read();
				if (byte_read == -1) {
					fw.close();

					System.out.print("::Filtering is finished; Output file is created.");
					return;
				} else {
					fw.write(byte_read); // ���� �޾Ƽ� Output1.txt�� �ۼ�

				}
			}
		} catch (Exception e) {
			closePorts();
			e.printStackTrace();

		}
	}

}
