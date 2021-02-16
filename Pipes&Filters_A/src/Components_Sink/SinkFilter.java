package Components_Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.GeneralFilter;

public class SinkFilter extends GeneralFilter {
	private String filePath;
	
	// Students.txt data�� �о filePath�� ����
	public SinkFilter(String outputFilePath) {
		this.filePath = outputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// �� byte ���� �μ�
		int byte_read = 0;

		try {
			// file ����
			FileWriter fw = new FileWriter(this.filePath);
			for (;;) {
				byte_read = in.read();
				if (byte_read == -1) { // �� ������ ����
					fw.close();
					System.out.print("::Filtering is finished; Output file is created.");
					return;
				} else { // �� �� �������� file�� �ۼ�
					fw.write(byte_read);
				}
			}
		} catch (Exception e) {
			closePorts();
			e.printStackTrace();

		}
	}

}
