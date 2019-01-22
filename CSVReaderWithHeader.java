import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVReaderWithHeader {
    private static final String PROJECT1_CSV = "./Project1.csv";
    private static final String SAMPLE_CSV_FILE_PATH = PROJECT1_CSV;

    public static void main(String[] args) throws IOException {
        try (
                
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter(';') 
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
                        
        ) {
            PgSQlWriter.initTable();
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String timestamp  = csvRecord.get("Timestamp");
                String feeder1 = csvRecord.get("Feeder1");
                String feeder2 = csvRecord.get("Feeder2");
                String temp = csvRecord.get("Temperature");
                PgSQlWriter.main(timestamp,feeder1,feeder2,temp);
                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Timestamp : " + timestamp);
                System.out.println("Feeder1 : " + feeder1);
                System.out.println("Feeder2 : " + feeder2);
                System.out.println("Temperature : " + temp);
                System.out.println("---------------\n\n");
            }
        }
    }

}