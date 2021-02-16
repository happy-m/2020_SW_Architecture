package Components_Source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.GeneralFilter;

public class SourceFilter extends GeneralFilter {
	private String filePath;

	// Students.txt data를 읽어서 filePath에 저장
	public SourceFilter(String inputFilePath) {
		this.filePath = inputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		// txt파일 읽은 것을 out에 써서 다음 filter인 MiddleFilter로 전달
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
