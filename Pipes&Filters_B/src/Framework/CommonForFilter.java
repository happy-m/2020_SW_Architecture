package Framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public interface CommonForFilter extends Runnable {

	public void connectOutputTo(int outputIndex, CommonForFilter previousFilter, int inputIndex) throws IOException;

	public void connectInputTo(int inputIndex, CommonForFilter previousFilter, int outputIndex) throws IOException;

	public PipedInputStream getPipedInputStream(int outputIndex);

	public PipedOutputStream getPipedOutputStream(int inputIndex);

}
