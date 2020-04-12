import java.io.IOException;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    public String analyze(String stringInput, BankStatementParser bankStatementParser) throws IOException {
        return analyzer(stringInput, bankStatementParser);
    }

    private String analyzer(String stringInput, BankStatementParser bankStatementParser) throws IOException {
        String results = "Welcome to Bank Statement Analyzer \n";

        List<BankTransaction> bankTransactionList = bankStatementParser.parseToList(stringInput);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactionList);

        results += "Total Amount: " + bankStatementProcessor.calculateTotalAmount() + "\n";
        results += "Total Amount for January: " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY) + "\n";
        results += "Total Amount for February: " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY) + "\n";
        results += "Total Amount for Rent: " + bankStatementProcessor.calculateTotalbyCategory("Rent") + "\n";
        results += "Bank Transactions filter by \"Tesco\":\n";
        results += bankStatementProcessor.getTransactionsByTesco() + "";
        results += "--- End of line ---";
        return results;
    }

}
