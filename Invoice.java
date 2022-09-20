import java.util.Collections;
import java.util.List;

public class Invoice {
   
    private String customer;
    private List<Performance> performances;

    Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }
    
    public String getCustomer() {
        return this.customer;
    }

    public List<Performance> getPerformanceList() {
        List<Performance> umodifiableList = Collections.unmodifiableList(this.performances);
        return umodifiableList;
    }
}
