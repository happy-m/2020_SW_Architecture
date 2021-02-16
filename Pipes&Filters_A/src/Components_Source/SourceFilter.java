package Components_Source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.GeneralFilter;

public class SourceFilter extends GeneralFilter {
	private String filePath;

	// Students.txt data�� �о filePath�� ����
	public SourceFilter(String inputFilePath) {
		this.filePath = inputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// txt���� ���� ���� out�� �Ἥ ���� filter�� MiddleFilter�� ����
		int byte_read;
		try {
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(filePath)));
			for (;;) {
				byte_read = br.read();
				if (byte_read == -1) {
					return;
				}
				out.write(byte_read);
			}
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
