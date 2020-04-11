import java.io.IOException;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    public String analyze(String stringInput, BankStatementParser bankStatementParser) throws IOException {
        return analyzer(stringInput, bankStatementParser);
    }

    private String analyzer(String stringInput, BankStatementParser bankStatementParser) throws IOException {
        String results = "Welcome to Bank Statement Analyzer \n";


        List<BankStatement> bankStatementList = bankStatementParser.parseToList(stringInput);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankStatementList);

        results += "Total Amount: " + bankStatementProcessor.getTotalAmount() + "\n";
        results += "Total Amount for January: " + bankStatementProcessor.getTotalInMonth(Month.JANUARY) + "\n";
        results += "Total Amount for February: " + bankStatementProcessor.getTotalInMonth(Month.FEBRUARY) + "\n";
        results += "Total Amount for Rent: " + bankStatementProcessor.getTotalbyCategory("Rent") + "\n";
        results += "--- End of line ---";
        return results;
    }

}
