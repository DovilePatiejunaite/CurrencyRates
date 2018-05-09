import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;
import java.io.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> codes = new ArrayList<String>();
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-d")){
                int j=i+1;
                while(!args[j].equals("-c")){
                    dates.add(args[j]);
               //     System.out.println(args[j]+"-dates");
                    i=j;
                    j++;
                    if(j== args.length){
                        break;
                    }
                }
            } else if(args[i].equals("-c")){
                int j=i+1;
                while(!args[j].equals("-d")){
                    codes.add(args[j]);
                //    System.out.println(args[j]+"-codes");
                    i=j;
                    j++;
                    if(j== args.length){
                        break;
                    }
                }
            }
           // System.out.println(args[i]+" args");
        }
        DataExtractor d = new DataExtractor();
        Counter c = new Counter();
        UrlFormatter u = new UrlFormatter();
        u.setCurrencyCodes(codes);
        u.setDates(dates);
        c.count( d.download(u.createUrl()));
        for(int i=0;i < codes.size() ;i++){
            System.out.println(codes.get(i));
        }
    }
}
