package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public abstract class GeneralFilter implements CommonForFilter {
	// �� byte�� ����
	protected ArrayList<PipedInputStream> in = new ArrayList<PipedInputStream>();
	protected ArrayList<PipedOutputStream> out = new ArrayList<PipedOutputStream>();
	
	 // input���Ϳ� output���Ϳ� 2���� ����� ����
	public GeneralFilter(int inPlace, int outPlace) {
		for(int i = 0; i < inPlace; i++) {
			in.add(new PipedInputStream());
		}
		for(int i = 0; i < outPlace; i++) {
			out.add(new PipedOutputStream());
		}
	}
	
	// ������ pipe�� ���� filter�� ���� pipe�� ����
	public void connectOutputTo(int outputIndex, CommonForFilter previousFilter, int inputIndex) throws IOException {
        out.get(outputIndex).connect(previousFilter.getPipedInputStream(inputIndex));
    }

	// �� filter���� ������ pipe��  ���� pipe�� ����
    public void connectInputTo(int inputIndex, CommonForFilter previousFilter, int outputIndex) throws IOException {
        in.get(inputIndex).connect(previousFilter.getPipedOutputStream(outputIndex));
    }
    
	public PipedInputStream getPipedInputStream(int index) {
		return in.get(index);
	}

	public PipedOutputStream getPipedOutputStream(int index) {
		return out.get(index);
	}

	/**********
	 * Implementation of public methods defined in Runnable interface
	 ************/

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
				for(int i = 0; i < out.size(); i++) {
					out.get(i).close();
				}
				for(int i = 0; i < in.size(); i++) {
					in.get(i).close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/********** Implementation of protected methods ************/

	protected void closePorts() {
		try {
			for(int i = 0; i < out.size(); i++) {
				out.get(i).close();
			}
			for(int i = 0; i < in.size(); i++) {
				in.get(i).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/********** Abstract method that should be implemented ************/

	abstract public void specificComputationForFilter() throws IOException;
}
