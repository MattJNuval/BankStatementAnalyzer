@FunctionalInterface
public interface BankTransactionSummarizer {
    public double summarize(double accumulator, BankTransaction bankTransaction);
}
