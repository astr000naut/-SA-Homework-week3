
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

class UnknowTypeException extends Exception {
    public UnknowTypeException(String type) {
        super("unknow type: " + type);
    }
}

public class Main {

    public static void updatePerformanceInfo(Invoice invoice, PlayList playList) throws UnknowTypeException {
        for (Performance performance: invoice.getPerformance()) {
            Play play = playList.getPlay(performance.getPlayId());
            performance.updatePlayName(play.getName());
            performance.updatePlayType(play.getType());
            performance.calculateCredit();
            performance.calculateAmount();
        }
    }

    public static String statement(Invoice invoice, PlayList playList) {
        try {
            NumberFormat nF = NumberFormat.getCurrencyInstance();
            nF.setCurrency(Currency.getInstance(Locale.US));
            nF.setMinimumFractionDigits(2);
            nF.setMaximumFractionDigits(2); 

            updatePerformanceInfo(invoice, playList);
            
            int totalAmount = 0;
            int volumeCredits = 0;
            String result = "Statement for " + invoice.getCustomer() + "\n";

            for (Performance performance : invoice.getPerformance()) {
                result +=
                    performance.getPlayName() + ": " +
                    nF.format(performance.getAmount()/100) + " " +
                    performance.getAudience() + " seats\n";

                totalAmount += performance.getAmount();
                volumeCredits += performance.getCredit();
            }
            result += "Amount owed is " + nF.format(totalAmount/100) + "\n";
            result += "You earned " + volumeCredits + " credits\n";
            return result;

        } catch (UnknowTypeException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static void main(String args[]) {

        PlayList playList = new PlayList();
        playList.addPlay("hamlet", "Hamlet", "tragedy");
        playList.addPlay("as-like", "As You Like It", "comedy");
        playList.addPlay("othello", "Othello", "tragedy");

        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

        Performance[] performanceList = new Performance[] {
            new Performance("hamlet", 55),
            new Performance("as-like", 35),
            new Performance("othello", 40),
        };

        invoiceList.add(new Invoice("BigCo", performanceList));

        for (Invoice invoice : invoiceList) {
            System.out.println(statement(invoice, playList));
        }
    }
}
