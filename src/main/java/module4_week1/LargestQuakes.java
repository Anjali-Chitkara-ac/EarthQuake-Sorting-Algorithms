package module4_week1;

import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double maxMag = 0;
        QuakeEntry greatestQuake = null;
        for(QuakeEntry qe : list){
            double currMag = qe.getMagnitude();
            if(currMag>maxMag){
                maxMag = currMag;
                greatestQuake = qe;
            }
        }
        System.out.println("Greatest quake " + "\n" + greatestQuake);
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double maxMag = 0;
        QuakeEntry greatestQuake = null;
        int indexOfLargest = 0;

        for(QuakeEntry qe : data){
            double currMag = qe.getMagnitude();
            if(currMag>maxMag){
                maxMag = currMag;
                greatestQuake = qe;
                indexOfLargest = data.indexOf(greatestQuake);
            }
        }
        //System.out.println("Index Of Largest is: " + indexOfLargest);
        return indexOfLargest;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(int k=0;k<howMany;k++) {
            int maxIndex = 0;
            for (int i = 0; i < copy.size(); i++) {
                QuakeEntry qe = copy.get(i);
                maxIndex = indexOfLargest(copy);
            }
            answer.add(copy.get(maxIndex));
            copy.remove(copy.get(maxIndex));
        }
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        return answer;
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

//        lq.findLargestQuakes();
//        lq.indexOfLargest(list);
        lq.getLargest(list,5);
    }
}
