package module4_week2;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import module4_week1.EarthQuakeParser;
import module4_week1.QuakeEntry;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData,int from){
      int maxIndex = from;
      for(int i=from;i<quakeData.size();i++){
          if(quakeData.get(i).getDepth()>quakeData.get(maxIndex).getDepth()){
              maxIndex = i;
          }
      }
      return maxIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
            for (int i = 0; i < 70; i++) {
                int maxIndex = getLargestDepth(in, i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qMax = in.get(maxIndex);
                in.set(i, qMax);
                in.set(maxIndex, qi);
            }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week2/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);

        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
	}

	public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i=0; i < quakeData.size() - numSorted; i++){

            QuakeEntry curr = quakeData.get(i);
            double mag = curr.getMagnitude();

            QuakeEntry nextEntry = quakeData.get(i+1);
            double nextMag = nextEntry.getMagnitude();

            if(mag>nextMag){
                quakeData.set(i,nextEntry);
                quakeData.set(i+1,curr);
            }
        }
        for(QuakeEntry qe : quakeData) {
            System.out.println(qe);
        }
        System.out.println("");
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){

        for (int i = 1; i < in.size(); i ++) {
            System.out.println("Pass " + i);
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        boolean b1 = false;
        for(int i=0;i<quakes.size()-1;i++){
            double currMag = quakes.get(i).getMagnitude();
            double nextMag = quakes.get(i+1).getMagnitude();
            if(nextMag>currMag){
                b1=true;
            }
            else {return false;}
        }
        return b1;
    }

    public void sortByBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int count =0;
            for (int i = 1; i < in.size(); i++) {
                if (!checkInSortedOrder(in)) {
                    //count ++;
                    count=i;
                    System.out.println("Pass " + i);
                    onePassBubbleSort(in, i);
                }
            }

        System.out.println("# passes required " + count);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int count =0;
        for (int i=0; i< in.size(); i++) {
            if (!checkInSortedOrder(in)) {
                count ++;
                //count=i;
                int minIdx = getSmallestMagnitude(in, i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i, qmin);
                in.set(minIdx, qi);
            }
        }
        System.out.println("# passes required " + count);
    }

    public static void main(String[] args) {
        QuakeSortInPlace qsip = new QuakeSortInPlace();
        qsip.testSort();
    }
}
