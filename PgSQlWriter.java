import java.io.IOException;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.postgresql.*;



public class PgSQlWriter {
    private static final String conn_string="jdbc:postgresql://127.0.0.1:5432/test";
    private static final String conn_user="fflex";
    private static final String conn_pass="";
    private static  Connection conn = null;
    private static  Random rand = new Random();

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(conn_string, conn_user, conn_pass);
    }
    public static void initTable(){
        String sql = "CREATE TABLE IF NOT EXISTS archive (eventdate text,feeder1 text,feeder2 text,temp  text,prediction int)";
        try (Connection conn=connect();
        Statement stmt = conn.prepareStatement(sql)) {
        stmt.execute(sql);
        stmt.close();
        conn.close();
} catch (SQLException e) {
    e.printStackTrace();
}
    }
    public static int updatePrediction(int val) {
        Integer update_value = 2*val;
        String sql = "UPDATE archive SET prediction=? WHERE (SELECT row_number() over () from archive LIMIT 1)=?";
        try (Connection conn=connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 2*val);
            pstmt.setInt(2,val);
            pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return update_value;
}
    public static void main(String timestamp,String feeder1, String feeder2, String temp) throws IOException {
        try {
        Class.forName("org.postgresql.Driver");
        conn = connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO archive (eventdate,feeder1,feeder2,temp,prediction) VALUES (?,?,?,?,?)");
                              ps.setString(1,timestamp);
                              ps.setString(2, feeder1);
                              ps.setString(3, feeder2);
                              ps.setString(4, temp);
                              ps.setInt(5,updatePrediction(rand.nextInt(1000)+1));
            ps.execute();
} catch (SQLException e) {
    e.printStackTrace();
} catch (ClassNotFoundException e) {
    e.printStackTrace();
} finally {
    try {conn.close();}
    catch (SQLException e) {e.printStackTrace();}
}

}
}

