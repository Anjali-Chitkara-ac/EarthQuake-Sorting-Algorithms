package module4_week2;
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import module4_week1.EarthQuakeParser;
import module4_week1.Location;
import module4_week1.QuakeEntry;

import java.util.*;

public class DifferentSorters {
    public void sortWithCompareTo() {

        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source ="/Users/anjali/Desktop/4. JP-Principles of Software Design/Week2/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        for(QuakeEntry qe: list) {
            int quakeNumber = list.indexOf(qe);
            System.out.println("Print quake entry in position " + quakeNumber);
            System.out.println(qe);
        }

    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        //list.sort(new DistanceComparator(where));  This and prev. line are one and the same. No difference at all
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByTitleAndDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source ="/Users/anjali/Desktop/4. JP-Principles of Software Design/Week2/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list,new TitleAndDepthComparator());
        for(QuakeEntry qe: list) {
            int quakeNumber = list.indexOf(qe);
            System.out.println("Print quake entry in position " + quakeNumber);
            System.out.println(qe);
        }

    }

    public void sortByLastWordInTitleThenByMagnitude(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source ="/Users/anjali/Desktop/4. JP-Principles of Software Design/Week2/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list,new TitleLastAndMagnitudeComparator());
        for(QuakeEntry qe: list) {
            int quakeNumber = list.indexOf(qe);
            System.out.println("Print quake entry in position " + quakeNumber);
            System.out.println(qe);
        }
    }

    public static void main(String[] args) {
        DifferentSorters df = new DifferentSorters();
        df.sortWithCompareTo();
        //df.sortByTitleAndDepth();
        //df.sortByLastWordInTitleThenByMagnitude();
    }
}
