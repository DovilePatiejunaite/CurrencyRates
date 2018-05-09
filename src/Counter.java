import model.Rate;
import java.util.*;
public class Counter {

    public void count(List<Rate> list){
        int counter = 0;
        float first = 0;
        float last = 0;
        for (int i = 0; i < list.size(); i++) {
          //  System.out.println(list.get(i).getRate());
            if(i==0){
                first = list.get(i).getRate();
            } else {
                if(!list.get(i).getCode().equals(list.get(i-1).getCode())){
                first = list.get(i).getRate();
                }
                if(i+1!=list.size()) {
                    if (!list.get(i).getCode().equals(list.get(i + 1).getCode())) {
                        last = list.get(i).getRate();
                        System.out.println(first + "first");
                        System.out.println(last + " last");
                    }
                } else {
                    last = list.get(i).getRate();
                    float change = last - first;
                    System.out.println(list.get(i).getCode()+" "+change);
                    System.out.println(first + "2first");
                    System.out.println(last + " 2last");

                }
            }
        }

    }
}
