import java.io.File;
import java.util.List;

public class DetectProjects {
    public static List<String> project_names;
    static final String valid_extension=".csv";
    static final File folder = new File("./");
    static File[] listOfFiles = folder.listFiles();

    public static void main(String[] args) {
for (int i = 0; i < listOfFiles.length; i++) {
  if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(valid_extension)) {
    project_names.add(listOfFiles[i].toString()); 
    System.out.println("File " + listOfFiles[i].getName());
  } else if (listOfFiles[i].isDirectory()) {
    System.out.println("Directory " + listOfFiles[i].getName());
  }
}
       }
}