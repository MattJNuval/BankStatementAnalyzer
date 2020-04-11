import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public final static String RESOURCES = "src/main/resources/";

    public static void main(String args[]) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a file name");
        String stringInput = input.next();

        BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        BankStatementParser bankStatementCSVParser = new BankStatementCSVParser();
        System.out.println(bankStatementAnalyzer.analyze(stringInput, bankStatementCSVParser));

    }
}
