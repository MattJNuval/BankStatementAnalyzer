@FunctionalInterface
public interface BankTransactionFilter {
    public boolean test(BankTransaction bankTransaction);
}
