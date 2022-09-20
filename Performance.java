public class Performance {

    private String playid;
    private String playName;
    private String playType;
    private int audience;
    private int credit;
    private int amount;

    Performance(String playid, int audience) {
        this.playid = playid;
        this.audience = audience;
        this.credit = 0;
        this.amount = 0;
    }
    
    public String getPlayId() {
        return this.playid;
    }

    public int getAudience() {
        return this.audience;
    }

    public String getPlayType() {
        return this.playType;
    }

    public String getPlayName() {
        return this.playName;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getCredit() {
        return this.credit;
    }

    public void updatePlayName(String playName) {
        this.playName = playName;
    }

    public void updatePlayType(String playType) {
        this.playType = playType;
    }

    public void calculateCredit() {
        this.credit += Math.max(this.getAudience() - 30, 0); 
        if (this.getPlayType() == "comedy") {
            this.credit += Math.floor(this.getAudience() / 5);
        }
    }

    private void calculateAmountTragedyType() {
        int amount = 40000;
        if (this.audience > 30) {
            amount += 1000 * (this.audience - 30);
        }
        this.amount = amount;
    }

    private void calculateAmountComedyType() {
        int amount = 30000;
        if (this.audience > 20) {
            amount += 10000 + 500 * (this.audience - 20);
        }
        amount += 300 * this.audience;
        this.amount = amount;
    }

    public void calculateAmount() throws UnknowTypeException {
        switch (this.getPlayType()) {
            case "tragedy":
                calculateAmountTragedyType();
                break;
            case "comedy":
                calculateAmountComedyType();
                break;
            default:
                throw new UnknowTypeException(this.getPlayType());
        }
    }

}