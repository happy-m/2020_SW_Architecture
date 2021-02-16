package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class GeneralFilter implements CommonForFilter {
	protected PipedInputStream in = new PipedInputStream();
	protected PipedOutputStream out = new PipedOutputStream();

	// 나가는 pipe와 다음 filter의 들어가는 pipe를 연결
	public void connectOutputTo(CommonForFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream());
	}

	// 전 filter에서 나오는 pipe와  들어가는 pipe를 연결
	public void connectInputTo(CommonForFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream());
	}

	public PipedInputStream getPipedInputStream() {
		return in;
	}

	public PipedOutputStream getPipedOutputStream() {
		return out;
	}

	// Main에서 thread.start()하면 run()실행해서 filter로 보냄
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
