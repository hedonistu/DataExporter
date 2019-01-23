import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVReaderWithHeader {
    private static final String current_path=System.getProperty("user.dir");
    private static List<String> project_files = new DetectProjects(current_path).project_valid_names;
    private static String project_name;
    public static void main(String[] args) throws IOException {
    
       for (String pfile : project_files) {
        System.out.println(pfile); 
        try (
                
                Reader reader = Files.newBufferedReader(Paths.get(pfile));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter(';') 
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
                        
        ) {
            project_name = pfile.substring(pfile.indexOf("/") + 1, pfile.indexOf(".")).replaceAll("/","");
            System.out.println(project_name);
            PgSQlWriter.initTable(project_name);
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String timestamp  = csvRecord.get("Timestamp");
                String feeder1 = csvRecord.get("Feeder1");
                String feeder2 = csvRecord.get("Feeder2");
                String temp = csvRecord.get("Temperature");
                PgSQlWriter.main(project_name,timestamp,feeder1,feeder2,temp);
                //System.out.println("Record No - " + csvRecord.getRecordNumber());
                //System.out.println("---------------");
                //System.out.println("Timestamp : " + timestamp);
                //System.out.println("Feeder1 : " + feeder1);
                //System.out.println("Feeder2 : " + feeder2);
                //System.out.println("Temperature : " + temp);
                //System.out.println("---------------\n\n");
            }
        }
    }}

}