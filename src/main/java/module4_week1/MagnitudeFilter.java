package module4_week1;


/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    private String filterName;


    public MagnitudeFilter(double min, double max,String name) {
        magMin = min;
        magMax = max;
        filterName = name;

    } 

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }

    @Override
    public String getName() {
        return filterName;
    }
}
