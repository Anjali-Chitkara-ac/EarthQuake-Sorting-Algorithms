package module4_week1;

public class PhraseFilter implements Filter {
    private String request;
    private String phrase;
    private String filterName;


    public PhraseFilter(String req, String phr,String name){
        request = req;
        phrase = phr;
        filterName = name;

    }

    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        int index = title.indexOf(phrase);
        if(request.equals("start") && title.startsWith(phrase)){
            return true;
        }
        if(request.equals("end") && title.endsWith(phrase)){
            return true;
        }
        if(request.equals("any") && index != -1){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return filterName;
    }

}
