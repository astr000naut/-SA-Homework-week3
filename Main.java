
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
    public static int calculateAmount(String type, int audience) throws UnknowTypeException {
        int amount;
        switch (type) {
                case "tragedy":
                amount = 40000;
                    if (audience > 30) {
                        amount += 1000 * (audience - 30);
                    }
                    break;
                case "comedy":
                amount = 30000;
                    if (audience > 20) {
                        amount += 10000 + 500 * (audience - 20);
                    }
                    amount += 300 * audience;
                    break;
                default:
                    throw new UnknowTypeException(type);
            }
        return amount;
    }

    public static String statement(Invoice invoice, PlayList playList) throws UnknowTypeException {

        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "Statement for " + invoice.getCustomer() + "\n";
        NumberFormat nF = NumberFormat.getCurrencyInstance();
        nF.setCurrency(Currency.getInstance(Locale.US));
        nF.setMinimumFractionDigits(2);
        nF.setMaximumFractionDigits(2);

        for (Repertoire repertoire : invoice.getPerformance()) {

            Play play = playList.getPlay(repertoire.getPlayId());
            int thisAmount = calculateAmount(play.getType(), repertoire.getAudience());;
            volumeCredits += Math.max(repertoire.getAudience() - 30, 0);

            if (play.getType() == "comedy") {
                volumeCredits += Math.floor(repertoire.getAudience() / 5);
            }

            result += play.getName() + ": " + nF.format(thisAmount/100) + " " + repertoire.getAudience() + " seats\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + nF.format(totalAmount/100) + "\n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }

    public static void main(String args[]) {

        PlayList playList = new PlayList();
        playList.addPlay("hamlet", "Hamlet", "tragedy");
        playList.addPlay("as-like", "As You Like It", "comedy");
        playList.addPlay("othello", "Othello", "tragedy");

        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

        Repertoire[] performances = new Repertoire[] {
            new Repertoire("hamlet", 55),
            new Repertoire("as-like", 35),
            new Repertoire("othello", 40),
        };

        invoiceList.add(new Invoice("BigCo", performances));

        for (Invoice invoice : invoiceList) {
            try {
                System.out.println(statement(invoice, playList));
            } catch (UnknowTypeException e) {
                e.printStackTrace();
            }
        }
    }
}
