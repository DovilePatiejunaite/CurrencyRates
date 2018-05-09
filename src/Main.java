import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> dates = new ArrayList<String>();
        List<String> codes = new ArrayList<String>();
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
        c.count( d.download("yolo"));
    }
}
