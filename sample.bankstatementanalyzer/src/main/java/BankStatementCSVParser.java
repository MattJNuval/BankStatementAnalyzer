import jdk.vm.ci.meta.Local;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    private final static String RESOURCES = "src\\main\\resources\\";
    private final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public List<BankStatement> parseToList(String stringInput) {
        try {
            final Path path = Paths.get(RESOURCES + stringInput);
            final List<String> lines = Files.readAllLines(path);
            return parser(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<BankStatement> parser(List<String> lines) {

        List<BankStatement> bankStatementList = new ArrayList<>();
        for(final String line : lines) {
            final String column[] = line.split(",");

            LocalDate date = LocalDate.parse(column[0], DATE_PATTERN);
            double amount = Double.parseDouble(column[1]);
            String description = column[2];

            BankStatement bankStatement = new BankStatement(date, amount, description);
            bankStatementList.add(bankStatement);
        }
        return bankStatementList;

    }

}
