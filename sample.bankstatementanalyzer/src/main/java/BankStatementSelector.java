import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankStatementSelector {

    public String select(String stringInput, BankStatementParser bankStatementParser) {
        Scanner input = new Scanner(System.in);

        List<BankTransaction> bankTransactionList = bankStatementParser.parseToList(stringInput);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactionList);
        SummaryStatistics summaryStatistics = bankStatementProcessor.calculateStatistics();

        int selector = 0;
        do {
            try {
                System.out.println("\nPlease select an option: " +
                        "\n1. Filter by category " +
                        "\n2. Filter by month " +
                        "\n3. Filter by amount " +
                        "\n4. Export" +
                        "\n9. Exit");
                selector = input.nextInt();
                switch (selector) {
                    case 1:
                        System.out.println("What category do you want filter by?");
                        final String filterInput = input.next();
                        System.out.println("Filtering by " + filterInput);
                        // TODO: Filter by category
                        System.out.println("Total amount by " + filterInput + ": " + bankStatementProcessor.calculateTotalbyCategory(filterInput));
                        System.out.println(bankStatementProcessor.printFilteredTransactions(bankTransaction -> bankTransaction.getDescription().equals(filterInput)));
                        break;

                    case 2:
                        boolean monthBreaker = false;
                        System.out.println("What month do you want filter by? (i.e January, February...)");
                        final String filterInput2 = input.next();
                        do {
                            System.out.println("Filtering by " + filterInput2);
                            for (Month month : Month.values()) {
                                if (month.name().equals(filterInput2.toUpperCase())) {
                                    System.out.println("Total amount by " + filterInput2 + ": " + bankStatementProcessor.calculateTotalInMonth(Month.valueOf(filterInput2.toUpperCase())));
                                    System.out.println(bankStatementProcessor.printFilteredTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == Month.valueOf(filterInput2.toUpperCase())));
                                    monthBreaker = false;
                                    break;
                                } else {
                                    monthBreaker = true;
                                }
                            }
                            if (monthBreaker == true) {
                                System.out.println("Please enter a valid month. (i.e January, February ...)");
                            }
                        } while (monthBreaker);
                        break;

                    case 3:
                        System.out.println("What amount do you want filter by?");
                        boolean typeBreaker = false;
                        do {
                            try {
                                final String filterInput3 = input.next();
                                final double parsedInput = Double.parseDouble(filterInput3);
                                System.out.println("Filtering by " + filterInput3);
                                System.out.println(bankStatementProcessor.printFilteredTransactions(bankTransaction -> bankTransaction.getAmount() <= parsedInput));
                                typeBreaker = false;
                            } catch (NumberFormatException e) {
                                typeBreaker = true;
                                System.out.println("Invalid input, please input a number");
                            }
                        } while (typeBreaker == true);
                        // TODO: Filter by amount
                        break;

                    case 4:
                        System.out.println("Please enter a file name: ");
                        final String inputString4 = input.next();
                        System.out.println("Exporting...");
                        Exporter exporter = new HtmlExporter();
                        BankSummaryHtmlWriter bankSummaryHtmlWriter = new BankSummaryHtmlWriter();
                        System.out.println(bankSummaryHtmlWriter.writeFile(inputString4, exporter.export(summaryStatistics)));
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please select an option");
                select(stringInput, bankStatementParser);
                break;
            }
        } while (selector != 9);
        return "";
    }
}
