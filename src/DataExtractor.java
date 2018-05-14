import java.math.BigDecimal;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import model.Rate;
public class DataExtractor {
    ArrayList<String> urls;

    DataExtractor(ArrayList<String> urls){
        this.urls = urls;
    }
    public List<Rate> download(UrlFormatter u) {
        List<Rate> currency = new ArrayList<>();
        for(int i=0;i<urls.size();i++) {
            try {
                String url = urls.get(i);
                URL ur = new URL(url);
                Scanner s = new Scanner(ur.openStream());
                s.nextLine();
                String input;
                if(!s.hasNextLine()){
                    System.out.println("!Šią dieną nebuvo paskelbti valiutų kursai, rodomi vėliausiai paskelbti.!");
                    while(!s.hasNextLine()){
                        s.close();
                        url = u.refactorUrl(url);
                        ur = new URL(url);
                        s = new Scanner(ur.openStream());
                        s.nextLine();
                    }
                }
                while(s.hasNextLine()) {
                    input = s.nextLine().replace("\"", "").replace(",", ".");
                    String[] all = input.split(";");
                    System.out.println(input.replace(";","  "));
                    BigDecimal rateDecimal = new BigDecimal(all[2]);
                    Rate rate = new Rate(all[0], all[1], rateDecimal);
                    currency.add(rate);
                }
            } catch (IOException ex) {

            }
        }
        return currency;
    }
}
