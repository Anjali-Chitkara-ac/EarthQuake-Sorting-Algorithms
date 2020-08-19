package module4_week2;
/**
 * Write a description of class DistanceComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import module4_week1.Location;
import module4_week1.QuakeEntry;

import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2. getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
    
}
 