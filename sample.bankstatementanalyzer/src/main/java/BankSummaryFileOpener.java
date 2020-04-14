import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BankSummaryFileOpener {

    public String openFile(File htmlFile) {
        try {
            if (!Desktop.isDesktopSupported()) {
                return "File not supported";
            }
            Desktop desktop = Desktop.getDesktop();
            if (htmlFile.exists()) {
                desktop.open(htmlFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Opening ...";
    }
}
