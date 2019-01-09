import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.simple.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

/*##
import codecs
+import csv
import json
import re
import sqlite3
from collections import OrderedDict
##
*/

class DataExporter implements CsvInput,CsvValidate,CsvOutput {
        public String[] rows;
        private final String[] available_formats = {"json","database"};
        @Override
/*def csv_input():
localdict = []
try:
with open(input_file, 'rb') as csvfile:
dialect = csv.Sniffer().sniff(csvfile.read(512))
csvfile.seek(0)
csvdict = csv.DictReader(csvfile, dialect=dialect, delimiter=',')
for row in map(OrderedDict, csvdict):
localdict.append(row)
except csv.Error:
print 'CSV Read data failed'
return localdict
*/

        public final void csvInput(String input_file) {

            try {
                CSVReader r = new CSVReader(new FileReader(input_file));
                do rows = r.readNext(); while ((rows = r.readNext()) != null);
                }
                catch (FileNotFoundException e) {
                e.printStackTrace();
                }
                catch (IOException e) {
                e.printStackTrace();
            }


        }


        @Override
        public final void  csvOutput(String output_file, String output_format) {
            for (String s : available_formats){
            if ( output_format.equals(s)) {
                switch (output_format) {
                    case "csv":
                        try {
                            CSVWriter w = new CSVWriter(new FileWriter(output_file));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    case "json":
                        JSONObject objs = new JSONObject();

                        try ( FileWriter f = new FileWriter(output_file)){
                            f.write(objs.toJSONString());
                            f.flush();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    case "db":
                        Statement stmt=null;
                        Connection connection = null;
/*
* cur.execute('''DROP TABLE IF EXISTS hotels''')
cur.execute('''CREATE TABLE IF NOT EXISTS hotels
(name text, address text, stars int , contact text, phone text,uri text)''')
* */

                        try {
                            Class.forName("org.sqlite.JDBC");
                            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                            stmt = connection.createStatement();
                            String q1 = "DROP TABLE IF EXISTS hotels";
                            stmt.execute(q1);
                            String q2 = "CREATE TABLE IF NOT EXISTS hotels" +
                                         "(name text, address text, stars int ,"+
                                         "  contact text, phone text,uri text))";
                            stmt.execute(q2);
                            stmt.close();
 /*try:
for row in globaldict:
columns = ', '.join(row.keys())
placeholders = ':' + ', :'.join(row.keys())
query = 'INSERT INTO hotels (%s) VALUES (%s)' % (columns, placeholders)
cur.execute(query, row)*/
                            for (String row : rows) {
                                PreparedStatement ps = connection.prepareStatement("INSERT INTO hotels (%s) VALUES (%s)");
                                ResultSet rs = ps.executeQuery();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            try {connection.close();}
                            catch (SQLException e) {e.printStackTrace();}
                        }

                    }
                }
            }
        }


        @Override
        public boolean csvValidateHotelName(String hotel_name) {
                return false;
        }

        @Override
        public boolean csvValidateHotelUrl(String hotel_url) {
                return false;
        }

        @Override
        public boolean csvValidateHotelRating(String hotel_url) {
                return false;
        }

        @Override
        public void csvValidate() {

        }
        public void DataExporter(CsvInput i ,CsvValidate v,CsvOutput o) {}
}
