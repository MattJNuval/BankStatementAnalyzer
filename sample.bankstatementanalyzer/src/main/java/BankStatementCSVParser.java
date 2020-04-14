import jdk.nashorn.internal.ir.CatchNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    private final static String RESOURCES = "src\\main\\resources\\";
    private final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public List<BankTransaction> parseToList(String fileName) {
        try {
            final Path path = Paths.get(RESOURCES + fileName);
            final List<String> lines = Files.readAllLines(path);
            return parser(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<BankTransaction> parser(List<String> lines) {

        Validator validator = new Validator(lines);
        Notification notification = validator.validate();
        if(notification.hasErrors()) {
            String results = "Total Errors: " + notification.getErrors().size() + "\n";
            int counter = 1;
            for(final String error : notification.getErrors()) {
                results += counter + ". " + error + "\n";
                counter++;
            }
            throw new Error(results);
        }

        List<BankTransaction> bankTransactionList = new ArrayList<>();
        for(final String line : lines) {
            final String column[] = line.split(",");

            LocalDate date = LocalDate.parse(column[0], DATE_PATTERN);
            double amount = Double.parseDouble(column[1]);
            String description = column[2];

            BankTransaction bankTransaction = new BankTransaction(date, amount, description);
            bankTransactionList.add(bankTransaction);
        }
        return bankTransactionList;
    }
}
