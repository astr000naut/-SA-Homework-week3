
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

class UnknowTypeException extends Exception {
    public UnknowTypeException(String type) {
        super("unknow type: " + type);
    }
}

public class Main {

    public static void updatePerformanceInfo(Invoice invoice, PlayList playList) throws UnknowTypeException {
        for (Performance perf: invoice.getPerformanceList()) {
            Play play = playList.getPlay(perf.getPlayId());
            perf.updatePlayName(play.getName());
            perf.updatePlayType(play.getType());
            perf.calculateCredit();
            perf.calculateAmount();
        }
    }

    public static String currencyFormat(int number) {
        NumberFormat nF = NumberFormat.getCurrencyInstance();
        nF.setCurrency(Currency.getInstance(Locale.US));
        nF.setMaximumFractionDigits(2); 
        return nF.format(number / 100);
    }

    public static String statement(Invoice invoice, PlayList playList) {
        try {
            updatePerformanceInfo(invoice, playList);
            
            int totalAmount = 0;
            int volumeCredits = 0;
            String statementHeader = "Statement for " + invoice.getCustomer() + "\n";
            String statementBody = "";
            String statementFooter;

            for (Performance perf : invoice.getPerformanceList()) {
                statementBody +=
                    perf.getPlayName() + ": " +
                    currencyFormat(perf.getAmount()) + " " +
                    perf.getAudience() + " seats\n";
                totalAmount += perf.getAmount();
                volumeCredits += perf.getCredit();
            }

            statementFooter = 
                "Amount owed is " + currencyFormat(totalAmount) + "\n" +
                "You earned " + volumeCredits + " credits\n";

            return statementHeader + statementBody + statementFooter;
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

        List<Performance> performances = new ArrayList<Performance>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));
  
        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
        invoiceList.add(new Invoice("BigCo", performances));

        for (Invoice invoice : invoiceList) {
            System.out.println(statement(invoice, playList));
        }
    }
}
