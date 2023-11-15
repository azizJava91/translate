import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class User extends ShowBase {
    public static void getTranslate(int direction, String word) throws Exception {
        List<String> base = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("wordBase.txt"))) {
            while (br.ready()) {
                base.add(br.readLine());
            }
            ListIterator<String> listIterator = base.listIterator();
            boolean checkBase = false;
            boolean checkLanguage = false;
            while (listIterator.hasNext()) {
                String line = listIterator.next();
                if (line.contains(word)) {
                    String[] words = line.split("-");
                    for (int i = 0; i < words.length; i++) {
                        if (words[0].equals(word) && direction == 1) {
                            System.err.println(word + " ===> " + " (eng): " + words[1] + " >>>>> " + " (rus): " + words[2]);
                            checkLanguage = true;
                            break;
                        } else if (words[1].equals(word) && direction == 2) {
                            System.err.println(word + " ===> " + " (aze): " + words[0] + " >>>>> " + " (rus): " + words[2]);
                            checkLanguage = true;
                            break;
                        } else if (words[2].equals(word) && direction == 3) {
                            System.err.println(word + " ===> " + " (aze): " + words[0] + " >>>>> " + " (eng): " + words[1]);
                            checkLanguage = true;
                            break;
                        }
                    }
                    if (!checkLanguage) System.out.println(word + " not found this language");
                    checkBase = true;
                }
            }
            if (!checkBase) System.err.println("''" + word + "'' not found in base");
        }
    }
}