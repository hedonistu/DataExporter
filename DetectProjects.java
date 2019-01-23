import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class DetectProjects {
    public static List<String> project_valid_names=new ArrayList<String>();
    static final String valid_extension=".csv";
    static File folder = new File("./");
    static File[] listOfFiles = folder.listFiles(new FilenameFilter(){
    
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(valid_extension);
        }
    });
    //public DetectProjects(String currentPath){};
    public DetectProjects(String path) {
        final File folder = new File(path);
        final File[] listOfFiles = folder.listFiles(new FilenameFilter(){
        
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(valid_extension);
            }
        }); 
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
              project_valid_names.add(listOfFiles[i].getPath());        
    }
   }
}
    public static void main(String[] args) {
for (int i = 0; i < listOfFiles.length; i++) {
  if (listOfFiles[i].isFile()) {
    project_valid_names.add(listOfFiles[i].getPath()); 
    System.out.println("File " + listOfFiles[i].getPath());
  } 
  }
}
       }
