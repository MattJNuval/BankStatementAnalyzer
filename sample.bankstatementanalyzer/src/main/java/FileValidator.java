import java.io.File;

public class FileValidator {

    private final static String RESOURCES = "src\\main\\resources\\";

    public boolean validateFile(String fileName) {
        boolean results = true;
        File file = new File(RESOURCES + fileName);
        if(!file.exists()) {
            results =false;
        }
        return results;
    }
}
