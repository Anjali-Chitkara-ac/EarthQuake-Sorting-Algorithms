package module4_week1;

import module4_week1.QuakeEntry;

/**
 * Write a description of interface Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter {
    public  boolean satisfies(QuakeEntry qe);

    public String getName();
}

