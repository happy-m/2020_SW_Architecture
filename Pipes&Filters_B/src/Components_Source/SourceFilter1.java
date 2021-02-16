package Components_Source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.GeneralFilter;

public class SourceFilter1 extends GeneralFilter {
	// Students.txt 저장할 인수
	private String filePath;
	private int outPlace;

	public SourceFilter1(String inputFilePath, int in, int out) { // student정보 읽음
		super(in, out);
		this.outPlace = out - 1;
		this.filePath = inputFilePath;
	}

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read;
		try {
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(filePath)));
			for (;;) {
				byte_read = br.read();
				if (byte_read == -1) {
					return;
				}
				// 0번지에 있는 내용, 즉 Students.txt 읽어서 MiddleFilter로 전달
				out.get(outPlace).write(byte_read); 
			}
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			try {
				out.get(outPlace).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}