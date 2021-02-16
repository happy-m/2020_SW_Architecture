package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class GeneralFilter implements CommonForFilter {
	protected PipedInputStream in = new PipedInputStream();
	protected PipedOutputStream out = new PipedOutputStream();

	// ������ pipe�� ���� filter�� ���� pipe�� ����
	public void connectOutputTo(CommonForFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream());
	}

	// �� filter���� ������ pipe��  ���� pipe�� ����
	public void connectInputTo(CommonForFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream());
	}

	public PipedInputStream getPipedInputStream() {
		return in;
	}

	public PipedOutputStream getPipedOutputStream() {
		return out;
	}

	// Main���� thread.start()�ϸ� run()�����ؼ� filter�� ����
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void closePorts() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	abstract public void specificComputationForFilter() throws IOException;
}
