import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementProcessor {

    private List<BankStatement> bankStatementList;

    public BankStatementProcessor(List<BankStatement> bankStatementList) {
       this.bankStatementList = bankStatementList;
    }

    public double getTotalAmount() {
        double total = 0d;
        for(final BankStatement bankStatement: bankStatementList) {
            total += bankStatement.getAmount();
        }
        return total;
    }

    public double getTotalInMonth(Month month) {
        double total = 0d;
        for(final BankStatement bankStatement: bankStatementList) {
            if(bankStatement.getDate().getMonth() == month) {
                total += bankStatement.getAmount();
            }
        }
        return total;
    }

    public double getTotalbyCategory(String category) {
        double total = 0d;
        for(final BankStatement bankStatement: bankStatementList) {
            if(bankStatement.getDescription().equals(category)) {
                total += bankStatement.getAmount();
            }
        }
        return total;
    }


}
