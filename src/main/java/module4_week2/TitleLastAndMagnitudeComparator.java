package module4_week2;

import module4_week1.QuakeEntry;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1,QuakeEntry q2){
        String title1 = q1.getInfo();
        String lastWord1 = title1.substring(title1.lastIndexOf(" ")+1);
        //System.out.println(lastWord1);

        String title2 = q2.getInfo();
        String lastWord2 = title2.substring(title2.lastIndexOf(" ")+1);
        //System.out.println(lastWord2);

        if(lastWord1.compareTo(lastWord2)<0){ return -1;}
        else if(lastWord1.compareTo(lastWord2)>0)return 1;
        else {
            if(Double.compare(q1.getMagnitude(),q2.getMagnitude())<0) return -1;
            if(Double.compare(q1.getMagnitude(),q2.getMagnitude())>0) return 1;
        }
        return 0;
    }
}
