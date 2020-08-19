package module4_week1;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {

    private ArrayList<Filter> filters;

    public MatchAllFilter(){
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public boolean satisfies(QuakeEntry qe){
        boolean b1 = false;
        for(Filter filter : filters) { //t //t //f
            if(filter.satisfies(qe)){
                b1 = true;
            } else {
                return false;
            }
        }
        return b1;
    }

    @Override
    public String getName() {
        String names = " ";
        for(Filter filter : filters){
           names = names + " " + filter.getName();
        }
        return names;
    }


}
