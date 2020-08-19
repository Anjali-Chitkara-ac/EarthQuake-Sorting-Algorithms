package module4_week1;

public class DistanceFilter implements Filter {
    private Location loc;
    private double maxdistance;
    private String filterName;


    public DistanceFilter(Location location, double distance,String name){
        loc = location;
        maxdistance = distance;
        filterName = name;

    }

    @Override
    public boolean satisfies(QuakeEntry qe){
        Location location = qe.getLocation();
        double currDist = location.distanceTo(loc);
        return currDist<maxdistance;
    }

    @Override
    public String getName() {
        return filterName;
    }
}
