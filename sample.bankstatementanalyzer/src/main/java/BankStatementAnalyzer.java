import java.io.IOException;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    public String analyze(String stringInput, BankStatementParser bankStatementParser) throws IOException {
        return analyzer(stringInput, bankStatementParser);
    }

    private String analyzer(String stringInput, BankStatementParser bankStatementParser) throws IOException {
        String results = "Welcome to Bank Statement Analyzer \n\n".toUpperCase();

        List<BankTransaction> bankTransactionList = bankStatementParser.parseToList(stringInput);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactionList);
        SummaryStatistics summaryStatistics = bankStatementProcessor.calculateStatistics();

        results += bankStatementProcessor.printAllTransactions() +"\n";
        results += "Total Amount: " + bankStatementProcessor.calculateTotalAmount() + "\n";
        results += "Max: " + summaryStatistics.getMax() + "\n";
        results += "Min: " + summaryStatistics.getMin() + "\n";
        results += "Average: " + summaryStatistics.getAverage() + "";



        return results;
    }

}
