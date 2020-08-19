package module4_week2;

import module4_week1.QuakeEntry;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        if(q1.getInfo().compareTo(q2.getInfo()) < 0 ){
            return -1;
        }
        else if(q1.getInfo().compareTo(q2.getInfo()) > 0 ){
            return 1;
        }
        else {
            if(Double.compare(q1.getDepth(),q2.getDepth()) < 0 ) { return -1;}
           else if(Double.compare(q1.getDepth(),q2.getDepth()) > 0 ) { return 1;}
        }
        return 0;
    }
}
