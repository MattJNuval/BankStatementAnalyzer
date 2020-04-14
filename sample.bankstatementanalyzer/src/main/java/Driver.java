import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public final static String RESOURCES = "src/main/resources/";

    public static void main(String args[]) throws IOException {
            Scanner input = new Scanner(System.in);

            System.out.println("Please enter a file name:");
            String stringInput = "";
            FileValidator fileValidator = new FileValidator();
            boolean fileBreaker = false;
            do {
                stringInput = input.next();
                if (fileValidator.validateFile(stringInput)) {

                    BankStatementAnalyzer bankTransactionAnalyzer = new BankStatementAnalyzer();
                    BankStatementParser bankStatementCSVParser = new BankStatementCSVParser();

                    System.out.println(bankTransactionAnalyzer.analyze(stringInput, bankStatementCSVParser));


                    BankStatementSelector bankStatementSelector = new BankStatementSelector();
                    System.out.println(bankStatementSelector.select(stringInput, bankStatementCSVParser));
                    fileBreaker = false;
                } else {
                    fileBreaker = true;
                    System.out.println("Invalid file input. Please input an existing file:");
                }
            } while (fileBreaker == true);
    }
}
