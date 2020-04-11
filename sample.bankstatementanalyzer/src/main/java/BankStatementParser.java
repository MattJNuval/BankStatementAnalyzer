import java.util.List;

public interface BankStatementParser {
    public List<BankStatement> parseToList(String stringInput);
    public List<BankStatement> parser(List<String> lines);
}
