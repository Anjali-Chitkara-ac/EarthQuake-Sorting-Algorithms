package module4_week1;

import java.util.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) {
                answer.add(qe);
            } 
        }

        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MagnitudeFilter(4.0,5.0,"MagnitudeFilter");
        ArrayList<QuakeEntry> m7  = filter(list, f);
//        for (QuakeEntry qe: m7) {
//            System.out.println(qe);
//        }

        Filter f1 = new DepthFilter(-35000.0,-12000.0,"DepthFilter");
        ArrayList<QuakeEntry> m8 = filter(m7, f1);
        for (QuakeEntry qe : m8){
            System.out.println(qe);
        }
        System.out.println("# quakes found " + m8.size());

        /* Location loc = new Location(35.42, 139.43);
        Filter f2 = new DistanceFilter(loc,10000000,"DistanceFilter");
        ArrayList<QuakeEntry> d1 = filter(list,f2);

        Filter f3 = new PhraseFilter("any","Japan","PhraseFilter");
        ArrayList<QuakeEntry> p1 = filter(d1,f3);
        for(QuakeEntry qe:p1){
            System.out.println(qe);
        }
        System.out.println("# quakes found " + p1.size());
     */
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    public static void main(String[] args) {
        EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
        //eqc2.quakesWithFilter();
        //eqc2.testMatchAllFilter();
        eqc2.testMatchAllFilter2();
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0,2.0,"MagnitudeFilter");
        Filter f2 = new DepthFilter(-100000.0,-10000.0,"DepthFilter");
        Filter f3 = new PhraseFilter("any","a","PhraseFilter");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);

        ArrayList<QuakeEntry> m1 = filter(list,maf);
        for(QuakeEntry qe : m1){
            System.out.println(qe);
        }
        System.out.println("# quakes found " + m1.size());

        String names = maf.getName();
        System.out.println("Filter names are " + names);
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0,3.0,"MagnitudeFilter");
        Location loc = new Location(36.1314, -95.9372);
        Filter f2 = new DistanceFilter(loc,10000000,"DistanceFilter");
        Filter f3 = new PhraseFilter("any","Ca","PhraseFilter");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);

        ArrayList<QuakeEntry> m1 = filter(list,maf);
        for(QuakeEntry qe : m1){
            System.out.println(qe);
        }
        System.out.println("# quakes found " + m1.size());

    }

}
