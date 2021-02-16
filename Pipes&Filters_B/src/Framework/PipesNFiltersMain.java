package Framework;

import Components_Middle.MiddleFilter;
import Components_Sink.SinkFilter1;
import Components_Sink.SinkFilter2;
import Components_Source.SourceFilter1;
import Components_Source.SourceFilter2;

public class PipesNFiltersMain {

	public static void main(String[] args) {
		try {

			CommonForFilter filter1 = new SourceFilter1("Students.txt", 1, 1); // 학생정보 읽은 것을 input필터 첫번째 번지에 넣어줌
			CommonForFilter filter2 = new SourceFilter2("Courses.txt", 1, 2); // 학생정보 읽은 것을 input필터 두번째 번지에 넣어줌

			CommonForFilter filter3 = new MiddleFilter(2, 2); // sourceFilter와 SinkFilter를 연결해주는데 2개의 번지를 가짐

			CommonForFilter filter4 = new SinkFilter1("Output1.txt", 1, 1); // filtering끝난 결과를 Output1.txt에 출력
			CommonForFilter filter5 = new SinkFilter2("Output2.txt", 1, 1); // filtering끝난 결과를 Output2.txt에 출력

			filter1.connectOutputTo(0, filter3, 0); // SourceFilter1과  MiddleFilter 0번지 연결
			filter2.connectOutputTo(1, filter3, 1); // SourceFilter2와 MiddleFilter 1번지 연결

			filter3.connectOutputTo(0, filter4, 0); // MiddleFilter 0번지와 SinkFilter연결
			filter3.connectOutputTo(1, filter5, 0); // MiddleFilter 1번지와 SinkFilter연결

			// thread 세팅
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