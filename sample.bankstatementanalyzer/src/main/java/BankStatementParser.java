import java.util.List;

public interface BankStatementParser {
    public List<BankTransaction> parseToList(String stringInput);
    public List<BankTransaction> parser(List<String> lines);
}
