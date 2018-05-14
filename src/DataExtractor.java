import java.math.BigDecimal;
import java.util.*;
import java.io.*;
import java.net.*;
import model.Rate;

//Klasė, skirta atsiųsti duomenis apie valiutų kursus

public class DataExtractor {
    private ArrayList<String> urls;

    DataExtractor(ArrayList<String> urls){
        this.urls = urls;
    }

    //Pagal surinktus url, atsisiunčiame valiutų kursus

    public List<Rate> download(UrlFormatter u) {
        System.out.println("\nVALIUTŲ KURSAI PASIRINKTU LAIKOTARPIU\n");
        List<Rate> rates = new ArrayList<>();
        try {
            for (String url : urls) {
                String date = u.getDateFromUrl(url);
                URL ur = new URL(url);
                Scanner s = new Scanner(ur.openStream());
                s.nextLine();
                String input;

                //Jei nurodyta data, jokių kursų nebuvo paskelbta, pakeičiame datą sumažinę viena diena
                //ir vėl siunčiame duomenis

                if (!s.hasNextLine()) {
                    while (!s.hasNextLine()) {
                        s.close();
                        url = u.refactorUrl(url);
                        ur = new URL(url);
                        s = new Scanner(ur.openStream());
                        s.nextLine();
                    }
                }

                //Atspausdiname visus valiutų kursus nurodytų datų laikotarpyje ir sudedame į objektų masyvą

                while (s.hasNextLine()) {
                    input = s.nextLine().replace("\"", "").replace(",", ".");
                    String[] all = input.split(";");
                    System.out.println(all[0] + " " + all[1] + " " + all[2] + " " + date);
                    BigDecimal rateDecimal = new BigDecimal(all[2]);
                    Rate rate = new Rate(all[0], all[1], rateDecimal);
                    rates.add(rate);
                }
                s.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return rates;
    }
}
