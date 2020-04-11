import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementTest {

    @Test
    public void shouldParseOneCorrectLine() throws IOException {

        String line = "10-04-2020,3000,Costco";
        List<String> lineList = new ArrayList<>();
        lineList.add(line);

        BankStatementParser bankStatementParser = new BankStatementCSVParser();
        List<BankStatement> bankStatementList = bankStatementParser.parser(lineList);
        BankStatement actual = bankStatementList.get(0);

        BankStatement expected = new BankStatement(LocalDate.of(2020, Month.APRIL, 10), 3000, "Costco");

        double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), actual.getDate());
        Assert.assertEquals(expected.getAmount(), actual.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
