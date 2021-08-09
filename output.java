import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class output {
    public static void main(String args[]) throws IOException {
        File directoryPath = new File("C:\\Users\\kirubakar\\Downloads\\resources\\");
        File filesList[] = directoryPath.listFiles((d, f) -> f.toLowerCase().endsWith(".json"));
        System.out.println("List of files and directories in the specified directory:");
        Read read = new Read();
        read.Json(filesList, directoryPath);
    }
}
class Read {
    public void Json(File filesList[], File directoryPath) {
        JSONParser parser = new JSONParser();
        String Regex = "^[(]('true'|'false').equals(.*)[)]$";
        ArrayList<String> fileData = new ArrayList<String>();
        for (File file : filesList) {
            try {
                String nameFile = directoryPath + "\\" + file.getName();
                Object obj = parser.parse(new FileReader(nameFile));
                JSONObject jsonObject = (JSONObject) obj;
                String expression = (String) jsonObject.get("expression");
                fileData.add(expression);
                Pattern p = Pattern.compile(Regex);
                Matcher m = p.matcher(expression);
                System.out.println(expression + " is ");
                if (m.matches() == true) {
                    System.out.println("valid Expression");
                } else {
                    System.out.println("Invalid Expression");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
