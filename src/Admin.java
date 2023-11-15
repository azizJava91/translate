import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Admin extends ShowBase {
    public static void addWord(String newWordAze, String newWordEng, String newWordRus) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("wordBase.txt", true))) {
            bw.write(newWordAze.trim() + "-" + newWordEng.trim() + "-" + newWordRus.trim());
            bw.newLine();
        }
        System.out.println("words: " + newWordAze + ", " + newWordEng + ", " + newWordRus + " added to base");
        showBase();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void deleteWord(String deleteWord) throws Exception {
        List<String> base = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("wordBase.txt"))) {
            while (br.ready())
                base.add(br.readLine());
            boolean found = false;
            for (Iterator<String> it = base.iterator(); it.hasNext(); ) {
                String line = it.next();
                if (line.contains(deleteWord)) {
                    it.remove();
                    found = true;
                }
            }
            if (!found) {
                System.err.println("select available word");
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("wordBase.txt"))) {
            for (String updateList : base) {
                if (!updateList.isEmpty()) {
                    bw.write(updateList + "\n");
                }
            }
        }
        System.out.println(deleteWord + " deleted from base");
        showBase();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void refactorWord(String oldWord, String newWord)  {
        List<String> base = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("wordBase.txt"))) {
            while (br.ready())
                base.add(br.readLine());
            boolean found = false;
            ListIterator<String> listIterator = base.listIterator();
            while (listIterator.hasNext()) {
                String line = listIterator.next();
                if (line.contains(oldWord)) {
                    String[] words = line.split("-");
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].equals(oldWord)) {
                            words[i] = newWord;
                        }
                    }
                    String newLine = String.join("-", words);
                    listIterator.remove();
                    listIterator.add(newLine);
                    System.out.println(oldWord + " refactored to =====> " + newWord);
                    found = true;
                }
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("wordBase.txt"))) {
                    for (String updateList : base) {
                        if (!updateList.isEmpty()) {
                            bw.write(updateList + "\n");
                        }
                    }
                }
            }
            if (!found)
                System.err.println("select available word");
            showBase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}