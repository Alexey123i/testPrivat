package client;

/**
 * Created by Alexey on 30.03.2017.
 */
public class Deposit {
    private String Name;
    private String Country;
    private String Type;
    private String Depositor;
    private String AccountID;
    private double AmoundOnDeposit;
    private double Profitability;
    private int TimeContraints;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDepositor() {
        return Depositor;
    }

    public void setDepositor(String depositor) {
        Depositor = depositor;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public double getAmoundOnDeposit() {
        return AmoundOnDeposit;
    }

    public void setAmoundOnDeposit(double amoundOnDeposit) {
        AmoundOnDeposit = amoundOnDeposit;
    }

    public double getProfitability() {
        return Profitability;
    }

    public void setProfitability(double profitability) {
        Profitability = profitability;
    }

    public int getTimeContraints() {
        return TimeContraints;
    }

    public void setTimeContraints(int timeContraints) {
        TimeContraints = timeContraints;
    }

    public Deposit(String name, String country, String type, String depositor, String accountID, double amoundOnDeposit, double profitability, int timeContraints) {

        Name = name;
        Country = country;
        Type = type;
        Depositor = depositor;
        AccountID = accountID;
        AmoundOnDeposit = amoundOnDeposit;
        Profitability = profitability;
        TimeContraints = timeContraints;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "Name='" + Name + '\'' +
                ", Country='" + Country + '\'' +
                ", Type='" + Type + '\'' +
                ", Depositor='" + Depositor + '\'' +
                ", AccountID='" + AccountID + '\'' +
                ", AmoundOnDeposit=" + AmoundOnDeposit +
                ", Profitability=" + Profitability +
                ", TimeContraints=" + TimeContraints +
                '}';
    }
}
