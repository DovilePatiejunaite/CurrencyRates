import sun.plugin2.os.windows.FLASHWINFO;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import model.Rate;
public class DataExtractor{
    String url;
    String[] date;
    String[] CurrencyCodes;

    public void setCurrencyCode(String[] currencyCode) {
        CurrencyCodes = currencyCode;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String[] getCurrencyCode() {
        return CurrencyCodes;
    }

    public String[] getDate() {
        return date;
    }

    public List<Rate> download(String url) {
        List<Rate> currency = new ArrayList<>();
        try {
            // opcija -c - toliau skaitoma, kad paduodami valiutų kodai
            // opcija -d - toliau paduodams datos - atskirtos tarpais arba periodai atskirti "/" .
            //Jeigu nepaduota jokių valiutų kodai, tik data - išvedama visų valiutų kursai, nuo datos pradžios iki pabaigos ir visų kursų santykiai
            //Jeigu paduoti tik valiutų kursai - išvedama paskutinės galimos kurso datos (dabartinės) visus

            URL ur = new URL("https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=ALL&ff=1&class=Eu&");
            Scanner s = new Scanner(ur.openStream());
            System.out.println("Pirma eilute");
            System.out.println(s.nextLine());
            System.out.println("TOLIAU");
            String input = "";
            while (s.hasNextLine()) {
                input = s.nextLine().replace("\"","").replace(",",".");
                String[] all = input.split(";");
                System.out.println(input);
                Rate rate = new Rate(all[0],all[1], Float.parseFloat(all[2]), all[3]);
                currency.add(rate);
            }
        } catch (IOException ex) {

        }
        return currency;
    }
}
