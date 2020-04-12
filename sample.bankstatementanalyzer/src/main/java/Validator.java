import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Validator {
    private List<String> lines;

    public Validator(final List<String> lines) {
        this.lines = lines;
    }

    public Notification validate() {
        Notification notification = new Notification();
        for(String line : lines) {
            final String column[] = line.split(",");
            final String date = column[0];
            final String amount = column[1];
            final String description = column[2];

            if (description.length() > 100) {
                notification.addError("Description is too long");
            }

            final LocalDate parsedDate;
            final DateTimeFormatter DATE_FORMATTER;
            try {
                DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                parsedDate = LocalDate.parse(date, DATE_FORMATTER);

                if (parsedDate.isAfter(LocalDate.now())) {
                    notification.addError("date cannot be in the future");
                }
            } catch (DateTimeParseException e) {
                notification.addError("Invalid format for date");
            }

            final double parseAmount;
            try {
                parseAmount = Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                notification.addError("Invalid format for amount");
            }
        }
        return notification;
    }
}
