package Framework;

import Components_Middle.AddPreClass;
import Components_Middle.MiddleFilter;
import Components_Sink.SinkFilter;
import Components_Source.SourceFilter;

public class PipesNFiltersMain {

    public static void main(String[] args) {
        try {
        	
            CommonForFilter filter1 = new SourceFilter("data/Students.txt");
            CommonForFilter filter2 = new SinkFilter("data/Output.txt");
            CommonForFilter filter3 = new MiddleFilter();
            CommonForFilter filter4 = new AddPreClass();
            
            // sourceFilter�� middleFilter ����
            filter1.connectOutputTo(filter3);
            // middleFilter�� AddPreClass ����
            filter3.connectOutputTo(filter4);
            // AddPreClass�� sinkFilter ����
            filter4.connectOutputTo(filter2);
            
            // thread ����
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            Thread thread4 = new Thread(filter4);
            
            // thread start
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}