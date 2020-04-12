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

    public List<BankTransaction> filterTransactionsBy(final BankTransactionFilter bankTransactionFilter) {
        List<BankTransaction> results = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransactionFilter.test(bankTransaction)) {
                results.add(bankTransaction);
            }
        }
        return results;
    }


    public String getTransactionsByTesco() {
        String results = "";
        List<BankTransaction> bankTransactionListByTesco = filterTransactionsBy(bankTransaction -> bankTransaction.getDescription().equals("Tesco"));
        for(final BankTransaction bankTransaction : bankTransactionListByTesco) {
            results += "{\"Date\":\"" + bankTransaction.getDate() +
                    "\",\"Amount\":\"" + bankTransaction.getAmount() +
                    "\",\"Category\":\"" + bankTransaction.getDescription() + "\"}\n";
        }
        return results;
    }

}
