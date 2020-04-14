@FunctionalInterface
public interface BankSummaryFileWriter {
    public String writeFile(String fileName, String content);
}
