import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Klasė, kurianti url, kurie skirti csv failų su duomenimis apie valiutų kursus, atsisiuntimui

public class UrlFormatter {
    private ArrayList<String> currencyCodes = new ArrayList<>();
    private List<String> dates;

//Set ir get metodai

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public ArrayList<String> getCurrencyCodes() {
        return currencyCodes;
    }

    //Kiekvienam valiutos kodui ir datai sudarome po url, skirtą duomenų atsisiuntimui

    public ArrayList<String> createUrls(){

        ArrayList<String> urls = new ArrayList<>();
        String urlForDownload = "https://www.lb.lt/lt/currency/exportlist/?csv=1&currency="; //Atsisiųsime csv formatu

        //Jei nėra jokių nurodytų valiutų kodų - url tuščias

        if(currencyCodes.isEmpty()){
             System.out.println("!Nėra jokių valiutų kodų!");
             return urls;
        }

        //Formuojame url

        for(int i=0;i<getCurrencyCodes().size();i++){
            String code = getCurrencyCodes().get(i);
            String first = urlForDownload+code+"&ff=1&class=Eu&type=day";

            //Jei nėra datų - imame šiandienos datą

            if(dates==null||dates.isEmpty()){
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                urls.add(first + "&date_from_day=" + date + "&date_to_day=" + date);
            } else {
                for (String date : dates) {
                    urls.add(first + "&date_from_day=" + date + "&date_to_day=" + date);
                }
            }
        }
        return urls;
    }

    //Iš gauto datų intervalo sudarome visų dienų sąrašą.

    public List<String> getDaysBetweenDates(String start, String end) {
        List<String> date = new ArrayList<>();
        try{
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(startDate);
            while (calendar.getTime().before(endDate)||calendar.getTime().equals(endDate)) {
                Date result = calendar.getTime();
                String resultS = new SimpleDateFormat("yyyy-MM-dd").format(result);
                date.add(resultS);
                calendar.add(Calendar.DATE, 1);
            }
        }
        catch (ParseException ex){
                System.out.println("!Nurodytos neteisingos datos!");
        }
        return date;
    }

    //Sumažinę datą viena diena, grąžiname pakeistą url

    public String refactorUrl(String url){
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(getDateFromUrl(url));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            Date dateBefore = cal.getTime();
            String newDate = new SimpleDateFormat("yyyy-MM-dd").format(dateBefore);

            //Lb skelbia valiutų kursus nuo 2014-09-30, todėl tai mažiausia galima data

            Date dateLast = new SimpleDateFormat("yyyy-MM-dd").parse("2014-09-29");
            if(dateBefore.before(dateLast)){
                newDate = "2014-09-30";
            }
            url= url.replaceAll(getDateFromUrl(url), newDate);
        } catch (ParseException ex){
            System.out.println(ex.getMessage());
        }
        return url;
    }

    // Iš url gauname datą.

    public String getDateFromUrl(String url){
        Pattern pattern = Pattern.compile(".*date_from_day=(.*)&.*");
        Matcher matcher = pattern.matcher(url);
        String oldDate="";
        if (matcher.find())
        {
            oldDate = matcher.group(1);
        }
        return oldDate;
    }
}
