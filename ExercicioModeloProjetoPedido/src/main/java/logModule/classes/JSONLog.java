package logModule.classes;

import usuarioLogadoModule.UsuarioLogadoService;
import java.io.FileWriter;
import java.io.IOException;
import logModule.interfaces.ILog;

public class JSONLog implements ILog {
       
    private static final String ARQUIVO = "log.json";
    
    @Override
    public void escrever(String mensagem) {
        try(FileWriter writer = new FileWriter (ARQUIVO, true)){
            
            String nomeUsuario;
            nomeUsuario = new UsuarioLogadoService.getNomeUsuario();
        }catch (IOException e) {
            
        }

    }
}
