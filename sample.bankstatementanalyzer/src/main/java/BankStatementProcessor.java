import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactionList;

    public BankStatementProcessor(List<BankTransaction> bankTransactionList) {
       this.bankTransactionList = bankTransactionList;
    }

    public double summarizeTransaction(final BankTransactionSummarizer bankTransactionSummarizer) {
       double results = 0d;
       for(final BankTransaction bankTransaction : bankTransactionList) {
           results = bankTransactionSummarizer.summarize(results, bankTransaction);
       }
       return results;
    }

    public double calculateTotalAmount() {
        return summarizeTransaction((accumulator, bankTransaction) -> accumulator + bankTransaction.getAmount());
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransaction((accumulator, bankTransaction) ->
                bankTransaction.getDate().getMonth() == month ? accumulator + bankTransaction.getAmount() : accumulator);
    }

    public double calculateTotalbyCategory(final String category) {
        return summarizeTransaction((accumulator, bankTransaction) ->
                bankTransaction.getDescription().equals(category) ? accumulator + bankTransaction.getAmount() : accumulator);
    }

    public SummaryStatistics calculateStatistics() {
        double max = 0;
        double min = 0;
        double average = 0;
        double sum = 0;
        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getAmount() >= max) {
                max = bankTransaction.getAmount();
            } else if(bankTransaction.getAmount() <= min) {
                min = bankTransaction.getAmount();
            }
            average += bankTransaction.getAmount();
        }
        average /= bankTransactionList.size();
        sum = summarizeTransaction((accumulator, bankTransaction) -> accumulator + bankTransaction.getAmount());
        SummaryStatistics summaryStatistics = new SummaryStatistics(sum, max, min, average);
        return summaryStatistics;
    }


    public List<BankTransaction> filterTransactionsBy(final BankTransactionFilter bankTransactionFilter) {
        List<BankTransaction> results = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransactionFilter.test(bankTransaction)) {
                results.add(bankTransaction);
            }
        }
        return results;
    }

    public String printAllTransactions() {
        String result = "DATE | AMOUNT | CATEGORY\n";
        for(final BankTransaction bankTransaction: bankTransactionList) {
            result += bankTransaction.getDate() + " | " + bankTransaction.getAmount() + " | " + bankTransaction.getDescription() + "\n";
        }
        return result;
    }

    public String printFilteredTransactions(final BankTransactionFilter bankTransactionFilter) {
        String results = "DATE | AMOUNT | CATEGORY\n";
        List<BankTransaction> bankTransactionListByCategory = filterTransactionsBy(bankTransactionFilter);
        for(final BankTransaction bankTransaction : bankTransactionListByCategory) {
            results += bankTransaction.getDate() + " | " + bankTransaction.getAmount() +" | " + bankTransaction.getDescription() +"\n";
        }
        return results;
    }



}
