package logModule.classes;


import classes.Pedido;
import java.io.FileWriter;
import java.io.IOException;
import logModule.interfaces.ILog;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import services.UsuarioLogadoService;

public class JSONLog implements ILog {
    
    private static final String ARQUIVO = "log.json";
    @Override
    public void escrever(String mensagem, Pedido pedido) {
        try (FileWriter writer = new FileWriter(ARQUIVO, true)) {
            
            
            String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
            
            LocalDateTime agora = LocalDateTime.now();
            String data = agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String hora = agora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            
            JSONObject logJson = new JSONObject();
            logJson.put("Nome_Usuario", nomeUsuario);
            logJson.put("Data", data);
            logJson.put("Hora", hora);
            logJson.put("Nome_de_Operacao", mensagem);
            
            logJson.put("Codigo_Pedido", pedido.getCodigo());  
            logJson.put("Nome_Cliente", pedido.getCliente().getNome());
            
            writer.write(logJson.toString() + System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        }
            
            
    }
}
