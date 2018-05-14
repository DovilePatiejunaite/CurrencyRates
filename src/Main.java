
public class Main {

    public static void main(String[] args) {

        UrlFormatter url = new UrlFormatter();

        //Galimi valiutų kodai

        String[] currencies;
        currencies = new String[]{"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB",
                "SEK", "SGD", "THB", "TRY", "USD", "ZAR"};

        //Skaitomi programos argumentai
        //Opcija -d leidžia pridėti laiko tarpą (nuo-iki)

        Boolean isEqual=false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-d")) {
                if (i + 1 == args.length) {
                    break;
                } else {
                    String[] b = args[i + 1].split("/");
                    if (b.length == 2) {
                        url.setDates(url.getDaysBetweenDates(b[0], b[1]));
                    } else {
                        System.out.println("!Neteisingai įvestas laikotarpis!");
                    }
                    break;
                }
            } else {
                for (String currency : currencies) {
                    if (args[i].equals(currency)) {
                        isEqual = true;
                        break;
                    } else {
                        isEqual = false;
                    }
                }
                if (isEqual) {
                    url.getCurrencyCodes().add(args[i]);
                } else {
                    System.out.println("!Neteisingas valiutos kodas -" + args[i] + "!");
                }
            }
            //
            DataExtractor data = new DataExtractor(url.createUrls());
            Counter c = new Counter();
            c.count(data.download(url));
        }
}
