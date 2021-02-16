package Framework;

import Components_Middle.MiddleFilter;
import Components_Sink.SinkFilter1;
import Components_Sink.SinkFilter2;
import Components_Source.SourceFilter1;
import Components_Source.SourceFilter2;

public class PipesNFiltersMain {

	public static void main(String[] args) {
		try {

			CommonForFilter filter1 = new SourceFilter1("Students.txt", 1, 1); // �л����� ���� ���� input���� ù��° ������ �־���
			CommonForFilter filter2 = new SourceFilter2("Courses.txt", 1, 2); // �л����� ���� ���� input���� �ι�° ������ �־���

			CommonForFilter filter3 = new MiddleFilter(2, 2); // sourceFilter�� SinkFilter�� �������ִµ� 2���� ������ ����

			CommonForFilter filter4 = new SinkFilter1("Output1.txt", 1, 1); // filtering���� ����� Output1.txt�� ���
			CommonForFilter filter5 = new SinkFilter2("Output2.txt", 1, 1); // filtering���� ����� Output2.txt�� ���

			filter1.connectOutputTo(0, filter3, 0); // SourceFilter1��  MiddleFilter 0���� ����
			filter2.connectOutputTo(1, filter3, 1); // SourceFilter2�� MiddleFilter 1���� ����

			filter3.connectOutputTo(0, filter4, 0); // MiddleFilter 0������ SinkFilter����
			filter3.connectOutputTo(1, filter5, 0); // MiddleFilter 1������ SinkFilter����

			// thread ����
			Thread thread1 = new Thread(filter1);
			Thread thread2 = new Thread(filter2);

			Thread thread3 = new Thread(filter3);

			Thread thread4 = new Thread(filter4);
			Thread thread5 = new Thread(filter5);

			// thread start
			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}