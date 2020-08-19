package module4_week1;

import java.util.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub

    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
                                                   double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for(int i=0;i<quakeData.size();i++){
            //if the location of an earthQuake is < than a dist of distMax from the location from, return the entry
            QuakeEntry quake = quakeData.get(i);
            //System.out.println("#1");
            //System.out.println(quake);
            Location current = quake.getLocation();
            //System.out.println(current);
            //System.out.println(current.distanceTo(from));

            //System.out.println("Max is "  + distMax);
            //System.out.println(current);
            if(current.distanceTo(from) < distMax){
                System.out.println(current.distanceTo(from) + quake.getInfo());
                answer.add(quake);
            }
        }
//        for (QuakeEntry qe : answer){
//            System.out.println(qe);
//        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry qe : quakeData){
            double depth = qe.getDepth();
            if(depth>minDepth && depth<maxDepth){
                answer.add(qe);
            }
        }
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("start")){
                if(title.startsWith(phrase)){
                    answer.add(qe);
                }
            }
            if (where.equals("end")){
                if(title.endsWith(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("any")){
                int index = title.indexOf(phrase);
                if(index != -1){
                    answer.add(qe);
                }
            }
        }
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list,5.0);
        System.out.println("Found " + bigQuakes.size() + " quakes that match the criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeToMe = filterByDistanceFrom(list,1000000.0 ,city);
        System.out.println("Found " + closeToMe.size() + " quakes that match the criteria");

    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> quakesOfDepth = filterByDepth(list,-10000,-8000);
        System.out.println("Found " + quakesOfDepth.size() + " quakes that match the criteria");

    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/anjali/Desktop/4. JP-Principles of Software Design/Week1/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> quakesByPhrase = filterByPhrase(list,"any","Creek");
        System.out.println("Found " + quakesByPhrase.size() + " quakes that match the criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public static void main(String[] args) {
        EarthQuakeClient eqc = new EarthQuakeClient();
        //eqc.bigQuakes();
        //eqc.closeToMe();
        //eqc.quakesOfDepth();
        eqc.quakesByPhrase();
    }
    
}
