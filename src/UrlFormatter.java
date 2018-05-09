import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
public class UrlFormatter {
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> currencyCodes = new ArrayList<>();
    public void setCurrencyCodes(ArrayList<String> currencyCode) {
        this.currencyCodes = currencyCode;
        System.out.println("opa");
    }

    public void setDates(ArrayList date) {
        this.dates = dates;
    }


    public ArrayList<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<String> createUrl(){
        ArrayList<String> urls = new ArrayList<>();
        String urlForDownload = "https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=";
        String date = "";
        for(int i=0;i<getCurrencyCodes().size();i++){
            String code = getCurrencyCodes().get(i);
            urlForDownload = urlForDownload+code+"&ff=1&class=Eu&type=day";
            if(dates.isEmpty()){
                date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                urls.add(urlForDownload + "&date_from_day=" + date + "&date_to_day=" + date);
                //https://www.lb.lt/lt/currency?class=Eu&type=day&date_day=2018-05-09&currency=&ff=1
                //https://www.lb.lt/currency/exportlist/?csv=1&currency=AUD&date_from_day=2018-05-01&date_to_day=2018-05-06
            } else {
                for(int j=0;j<getDates().size();j++){
                    date = getDates().get(j);
                    String[] dates = date.split("-");
                    urlForDownload = urlForDownload + "&date_from_day=" + dates[0] + "&date_to_day=" + dates[1];
                    urls.add(urlForDownload);
                }
            }
        }
        return urls;
    }
}
