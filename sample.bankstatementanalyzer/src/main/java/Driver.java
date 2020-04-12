import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public final static String RESOURCES = "src/main/resources/";

    public static void main(String args[]) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a file name");
        String stringInput = input.next();

        BankStatementAnalyzer bankTransactionAnalyzer = new BankStatementAnalyzer();
        BankStatementParser bankStatementCSVParser = new BankStatementCSVParser();

        System.out.println(bankTransactionAnalyzer.analyze(stringInput, bankStatementCSVParser));

    }
}
