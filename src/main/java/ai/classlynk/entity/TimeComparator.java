package ai.classlynk.entity;

import java.time.LocalTime;
import java.util.Comparator;

public class TimeComparator implements Comparator<SClass> {
    public int compare(SClass c1, SClass c2) {
        LocalTime time1 = LocalTime.parse(c1.getStartTime());
        LocalTime time2 = LocalTime.parse(c2.getStartTime());
        return time1.compareTo(time2);
    }
}