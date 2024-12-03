/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Pedro Vitor
 */
public class Scripts {
    private static String url = "jdbc:sqlite:exercicioModeloProjetoPedido.db";
    public Scripts(){
    Scripts.criarBD();

    }
    
    public static void criarBD(){
        try  {
            var conn = DriverManager.getConnection(url);
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
       var sql = "CREATE TABLE IF NOT EXISTS Log ("
                + "	idLog INTEGER PRIMARY KEY,"
                + "	log TEXT NOT NULL,"
                + "	nomeUsuario TEXT NOT NULL"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /* public static void connect() {
        // connection string
        var url = "jdbc:sqlite:c:/sqlite/db/exercicioModeloProjetoPedido.db";

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } */
    
     /* public static void criarTabela(){

        var url = "jdbc:sqlite:exercicioModeloProjetoPedido.db";

        var sql = "CREATE TABLE IF NOT EXISTS Log ("
                + "	idLog INTEGER PRIMARY KEY,"
                + "	data DATE NOT NULL,"
                + "	log TEXT NOT NULL,"
                + "	nomeUsuario TEXT NOT NULL,"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }  */
    public  void inserir(String log,String nomeUsuario){
             String sql = "INSERT INTO Log(idLog,log,nomeUsuario) VALUES(?,?,?)";
             try (var conn = DriverManager.getConnection(url);
             var pstmt = conn.prepareStatement(sql)) {
                 pstmt.setString(1,"Null");
                 pstmt.setString(2,log);
                 pstmt.setString(3,nomeUsuario);
                 
             
             
             }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
              
    }
    
    
}

    

