package model;
import java.math.BigDecimal;

public class Rate{
    private String name;
    private String code;
    private BigDecimal rate;


    public Rate( String name, String code, BigDecimal rate){
        try{
            this.name = name;
            this.code = code;
            this.rate = rate;
        } catch (IllegalArgumentException e){
            e.getMessage();
        }
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
