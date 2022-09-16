public class Invoice {
   
    private String customer;
    private Repertoire[] performances;

    Invoice(String customer, Repertoire[] performances) {
        this.customer = customer;
        this.performances = performances;
    }
    
    public String getCustomer() {
        return this.customer;
    }

    public Repertoire[] getPerformance() {
        return this.performances;
    }
}
