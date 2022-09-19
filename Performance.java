public class Performance {

    private String playid;
    private int audience;

    Performance(String playid, int audience) {
        this.playid = playid;
        this.audience = audience;
    }
    
    public String getPlayId() {
        return this.playid;
    }

    public int getAudience() {
        return this.audience;
    }

}