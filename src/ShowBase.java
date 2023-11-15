import java.io.BufferedReader;
import java.io.FileReader;

public class ShowBase {
    public static void showBase() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("wordBase.txt"))) {
            System.err.println("all words in base:\n" + "======================================");
            while (br.ready()) {
                System.err.println(br.readLine() + "\n" + "======================================");
            }
        }
    }
}
