import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BankSummaryHtmlWriter implements BankSummaryFileWriter {

    private final static String RESOURCES = "src\\main\\resources\\";

    @Override
    public String writeFile(String fileName, String content) {
        File htmlFile = new File(RESOURCES + fileName + ".html");
        BankSummaryFileOpener bankSummaryFileOpener = new BankSummaryFileOpener();
        try {
            if(htmlFile.createNewFile()) {
                FileWriter htmlWriter = new FileWriter(RESOURCES + fileName + ".html");
                htmlWriter.write(content);
                htmlWriter.close();
                System.out.println("The file \"" + htmlFile.getName() + "\" has been created an written successfully");
                System.out.println(bankSummaryFileOpener.openFile(htmlFile));
            } else {
                System.out.println("File already exists.");
                FileWriter htmlWriter = new FileWriter(RESOURCES + fileName + ".html");
                htmlWriter.write(content);
                htmlWriter.close();
                System.out.println(bankSummaryFileOpener.openFile(htmlFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File processed successfully";
    }
}
