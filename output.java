import java.io.File;

import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json {
    public static void main(String args[]) throws IOException {
       
        JSONParser parser = new JSONParser();

        File directoryPath = new File("C:\\Users\\kirubakar\\Downloads\\resources\\");

        File filesList[] = directoryPath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean result;
                if (name.endsWith(".json")) {
                    result = true;
                } else {
                    result = false;
                }
                return result;
            }
        });

        System.out.println("List of files and directories in the specified directory:");

        ArrayList<String> fileData = new ArrayList<String>();

        for (File file : filesList) {

            String nameFile = directoryPath + "\\" + file.getName();

            try {
                Object obj = parser.parse(new FileReader(nameFile));
                JSONObject jsonObject = (JSONObject) obj;

                String expression = (String) jsonObject.get("expression");
                fileData.add(expression);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
 String Regex = " ^\\(\\'true'\\.equals\\(.*\\)\\)$";
        for (int i = 0; i < fileData.size(); i++) {

            Pattern p = Pattern.compile(Regex);
            Matcher m = p.matcher(fileData.get(i));
            System.out.println(fileData.get(i) + " is ");
              if (m.matches() == true) {
                System.out.println("valid Expression");
            } else {
                System.out.println("Invalid Expression");

            }
           
        }
    }
}
