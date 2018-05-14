import com.sun.org.apache.xpath.internal.operations.Bool;

public class Main {

    public static void main(String[] args) {
        UrlFormatter url = new UrlFormatter();
        String[] currencies;
        currencies = new String[]{"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB",
                "SEK", "SGD", "THB", "TRY", "USD", "ZAR"};
        Boolean isEqual=false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                if (i + 1 == args.length) {
                    break;
                }
                url.setDates(args[i + 1]);
                break;
            } else {
                for(int j = 0; j<currencies.length;j++) {
                    if(args[i].equals(currencies[j])) {
                        isEqual = true;
                        break;
                    } else {
                        isEqual = false;
                    }
                }
                if(isEqual){
                    url.getCurrencyCodes().add(args[i]);
                } else {
                    System.out.println("!Neteisingas valiutos kodas -"+args[i]+"!");
                }
            }
        }
            DataExtractor data = new DataExtractor(url.createUrls());
            Counter c = new Counter();
            c.count(data.download(url));
        }
}
