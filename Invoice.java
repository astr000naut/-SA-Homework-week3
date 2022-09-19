public class Invoice {
   
    private String customer;
    private Performance[] performances;

    Invoice(String customer, Performance[] performances) {
        this.customer = customer;
        this.performances = performances;
    }
    
    public String getCustomer() {
        return this.customer;
    }

    public Performance[] getPerformance() {
        return this.performances;
    }
}
