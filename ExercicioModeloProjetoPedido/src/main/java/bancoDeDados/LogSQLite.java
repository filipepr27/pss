package bancoDeDados;

import java.sql.*;

public class LogSQLite {
    private static final String URL = "jdbc:sqlite:exercicioModeloProjetoPedido.db";

    public LogSQLite() {
        LogSQLite.criarBD();
    }
    
    public static void criarBD(){
        String sql = """
                     CREATE TABLE IF NOT EXISTS Log (
                         idLog INTEGER PRIMARY KEY AUTOINCREMENT,
                         log TEXT NOT NULL,
                         nomeUsuario TEXT NOT NULL
                     );
                     """;

        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            System.out.println("Banco de dados conectado.");
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar/buscar tabela: " + e.getMessage());
        }
    }

    public  void inserir(String log,String nomeUsuario){
             String sql = "INSERT INTO Log(log,nomeUsuario) VALUES(?,?)";
             try (var conn = DriverManager.getConnection(URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                 pstmt.setString(1,log);
                 pstmt.setString(2,nomeUsuario);
                 pstmt.executeUpdate();
                 System.out.println("Log inserido com sucesso.");
             } catch (SQLException e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}

    

