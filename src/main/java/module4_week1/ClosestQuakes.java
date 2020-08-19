package module4_week1;
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {

    public double getClosest(ArrayList<QuakeEntry> quakeData, Location current) {
        double minDistance = Double.MAX_VALUE;
        for(int i = 0 ; i < quakeData.size(); i ++){ //0
            Location loc = quakeData.get(i).getLocation();
            double dist = loc.distanceTo(current);
            minDistance = Math.min(minDistance, dist);
        }

        return minDistance;
    }

    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(int j = 0; j < howMany; j ++) {
            int minIndex = 0;
            double distance = Double.MAX_VALUE;
            for (int i = 0; i < copy.size(); i++) {
                Location loc = copy.get(i).getLocation();
                double currentDist = loc.distanceTo(current);
                if (currentDist < distance) {
                    minIndex = i;
                    distance = currentDist;
                }
            }
            answer.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedatasmall.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for (QuakeEntry entry : close) {
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, entry);
        }
        System.out.println("number found: "+close.size());
    }

    public static void main(String[] args) {
        ClosestQuakes cq = new ClosestQuakes();
        cq.findClosestQuakes();

    }
    
}
