public class Performance {

    private String playid;
    private int audience;
    private String playName;
    private String playType;
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

    public void calculateAmount() throws UnknowTypeException {
        int temp_amount = 30000;
        switch (this.getPlayType()) {
            case "tragedy":
                temp_amount += 10000;
                if (this.getAudience() > 30) {
                    temp_amount += 1000 * (this.getAudience() - 30);
                }
                break;
            case "comedy":
                if (this.getAudience() > 20) {
                    temp_amount += 10000 + 500 * (this.getAudience() - 20);
                }
                temp_amount += 300 * this.getAudience();
                break;
            default:
                throw new UnknowTypeException(this.getPlayType());
        }
        this.amount = temp_amount;
    }

}