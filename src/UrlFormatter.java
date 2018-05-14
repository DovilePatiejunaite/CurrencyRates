import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFormatter {
    private String dates;
    private ArrayList<String> currencyCodes = new ArrayList<>();

    public void setDates(String dates){
        this.dates = dates;
    }
    public ArrayList<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public String getDates() {
        return dates;
    }

    public ArrayList<String> createUrls(){
        ArrayList<String> urls = new ArrayList<>();
        String urlForDownload = "https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=";
        String date;
        if(currencyCodes.isEmpty()){
             System.out.println("!Nėra jokių valiutų kodų!");
             return urls;
        }
        for(int i=0;i<getCurrencyCodes().size();i++){
            String code = getCurrencyCodes().get(i);
            String first = urlForDownload+code+"&ff=1&class=Eu&type=day";
            if(dates==null){
                date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                urls.add(first + "&date_from_day=" + date + "&date_to_day=" + date);
                //https://www.lb.lt/currency/exportlist/?csv=1&currency=AUD&date_from_day=2018-05-01&date_to_day=2018-05-06
            } else {
                    String[] dates = getDates().split("/");
                    urls.add(first + "&date_from_day=" + dates[0] + "&date_to_day=" + dates[1]);
            }
        }
        return urls;
    }

    //Jei data nieko negrazina - perdarome data;
    public String refactorUrl(String url){
        try{

            Pattern pattern = Pattern.compile(".*date_from_day=(.*)&.*");
            Matcher matcher = pattern.matcher(url);
            String oldDate="";
            if (matcher.find())
            {
                oldDate = matcher.group(1);
            }
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            Date dateBefore = cal.getTime();
            String newDate = new SimpleDateFormat("yyyy-MM-dd").format(dateBefore);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-09-29");
            if(dateBefore.before(date2)){
                newDate = "2014-09-30";
            }
            url= url.replaceAll(oldDate, newDate);
        } catch (ParseException w){}
        return url;
    }

}
