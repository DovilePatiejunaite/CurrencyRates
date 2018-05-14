import model.Rate;
import java.math.BigDecimal;
import java.util.*;

public class Counter {
    //Funkcija, apskaičiuojanti pokytį tarp valiutų kursų.
     public void count(List<Rate> list){
         System.out.println("\nPOKYČIAI\n");
         BigDecimal first =  new BigDecimal(0);
         BigDecimal last;
         //Gautas objektų sąrašas buvo sudėtas pagal datas mažėjimo tvarka.
         for (int i = 0; i < list.size(); i++) {
             if(i==0){
                 first = list.get(i).getRate();
             } else {
                 if (!list.get(i).getCode().equals(list.get(i - 1).getCode())) {
                     first = list.get(i).getRate();
                 }
             }
             if(i+1!=list.size()) {
                 if (!list.get(i).getCode().equals(list.get(i + 1).getCode())) {
                     last =  list.get(i).getRate();
                     BigDecimal change = first.subtract(last);
                     System.out.println(list.get(i).getName()+" "+list.get(i).getCode()+" "+change);
                 }
             } else {
                last = list.get(i).getRate();
                BigDecimal change = first.subtract(last);
                System.out.println(list.get(i).getName()+" "+list.get(i).getCode()+" "+change);
             }
         }
     }
}
