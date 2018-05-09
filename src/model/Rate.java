package model;

public class Rate {
    String name;
    String code;
    Float rate;
    String date;

    public Rate( String name, String code, float rate, String date){
        try{
            this.date = date;
            this.name = name;
            this.code = code;
            this.rate = rate;
        } catch (IllegalArgumentException e){
            e.getMessage();
        }
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
