package DeBiebApp.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=bibliotheek;user=java;password=javajavajava;";
    private Connection con = null;
    private Statement stmt = null;
    public DB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet selectQuery(String query){
        ResultSet res=null;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return res;
    }
    public void DSLQuery(String query){
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            this.con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}