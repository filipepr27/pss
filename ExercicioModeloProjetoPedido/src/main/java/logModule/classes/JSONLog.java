package logModule.classes;

import usuarioLogadoModule.UsuarioLogadoService;
import java.io.FileWriter;
import java.io.IOException;
import logModule.interfaces.ILog;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

public class JSONLog implements ILog {
       
    private static final String ARQUIVO = "log.json";
    
    @Override
    public void escrever(String mensagem) {
        try(FileWriter writer = new FileWriter (ARQUIVO, true)){
            
            UsuarioLogadoService nomeUsuario = new UsuarioLogadoService();
            nomeUsuario.getNomeUsuario();
            
            LocalDateTime agora = LocalDateTime.now();
            String data = agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String hora = agora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            
            
            JSONObject logJson = new JSONObject();
            logJson.put("Nome_Usuario",nomeUsuario);
            logJson.put("Data",data);
            logJson.put("Hora",hora);
            //logJson.put("codigo_pedido",..);
            logJson.put("Nome_de_Operacao", mensagem);
            //logJson.put("Nome_Cliente",nomecliente);
            
            writer.write(logJson.toString() + System.lineSeparator());
            
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
