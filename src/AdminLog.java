import java.io.BufferedReader;
import java.io.FileReader;

public class AdminLog {
    public static String login(String username, String password) throws Exception {
        String result = null;
        try (BufferedReader br = new BufferedReader(new FileReader("adminLog.txt"))){
            while (br.ready()) {
                String[] adminDetail = br.readLine().split("-");
                if (username.equals(adminDetail[0]) && password.equals(adminDetail[1])) {
                    String message = "welcome " + adminDetail[2];
                    System.out.println(message);
                    result = message;
                }
            }
        }
        return result;
    }
}