import java.util.ArrayList;
public class UrlFormatter {
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> currencyCodes = new ArrayList<>();
    public void setCurrencyCode(ArrayList currencyCode) {
        this.currencyCodes = currencyCode;
    }

    public void setDate(ArrayList date) {
        this.dates = dates;
    }


    public ArrayList<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<String> createUrl(){

        ArrayList<String> urls = new ArrayList<>();
        return urls;
    }
}
