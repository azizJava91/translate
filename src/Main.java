import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("are you admin or user ?");
            Scanner sc = new Scanner(System.in);
            String choise = sc.next().toLowerCase().trim();
            switch (choise) {
                case "admin" -> {
                    System.out.println("enter username : ");
                    String username = sc.next();
                    System.out.println("enter password : ");
                    String password = sc.next();
                    String logChek = AdminLog.login(username, password);
                    if (logChek == null) {
                        System.out.println("invalid username or password");
                        main(args);
                    }
                    System.out.println("enter operation : ");
                    System.out.println("""
                                    1: for enter new word
                                    2: for delete any word
                                    3: refactor any word
                            """);
                    int operation = sc.nextInt();
                    switch (operation) {
                        case 1 -> {
                            Admin.showBase();
                            System.out.println("enter  new word aze:");
                            String newWordAze = sc.next();
                            System.out.println("enter new word eng: ");
                            String newWordEng = sc.next();
                            System.out.println("enter new word rus: ");
                            String newWordRus = sc.next();
                            Admin.addWord(newWordAze, newWordEng, newWordRus);
                        }
                        case 2 -> {
                            Admin.showBase();
                            System.out.println("select deleting word");
                            String deletingWord = sc.next();
                            Admin.deleteWord(deletingWord);
                        }
                        case 3 -> {
                            Admin.showBase();
                            System.out.println("select the word you want change ");
                            String changeWord = sc.next();
                            System.out.println("enter the new word");
                            String newWord = sc.next();
                            Admin.refactorWord(changeWord, newWord);
                        }
                        default -> System.out.println("invalid operation");
                    }
                }
                case "user" -> {
                    System.out.println("select operation :");
                    System.out.println("""
                            1: for see word base
                            2: for select get translate
                            """);
                    int operation = sc.nextInt();
                    switch (operation) {
                        case 1 -> User.showBase();
                        case 2 -> {
                            System.out.println("enter direction");
                            System.out.println("""
                                    1: from aze -> to (eng, rus)
                                    2: from eng -> to (aze, rus)
                                    3: from rus -> to (aze, eng)
                                    """);
                            int direction = sc.nextInt();
                            System.out.println("write word");
                            String word = sc.next();
                            User.getTranslate(direction, word);
                        }
                        default -> System.out.println("invalid operation");
                    }
                }
                default -> System.out.println("invalid choise");
            }
             main(args);
        } catch (InputMismatchException e) {
            System.out.println("insert correct characters ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
