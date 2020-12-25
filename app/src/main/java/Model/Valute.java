package Model;

public class Valute {

    public String NameValuteStr;
    public String CODE;
    public String CURS;
    public String Amount;

    public Valute(String nameValuteStr, String CODE, String CURS, String amount) {
        NameValuteStr = nameValuteStr;
        this.CODE = CODE;
        this.CURS = CURS;
        Amount = amount;
    }

    public String getNameValuteStr() {
        return NameValuteStr;
    }

    public void setNameValuteStr(String nameValuteStr) {
        NameValuteStr = nameValuteStr;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getCURS() {
        return CURS;
    }

    public void setCURS(String CURS) {
        this.CURS = CURS;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
