package logModule.classes;

import classes.Pedido;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import logModule.interfaces.ILog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class JSONLog implements ILog {
    
    private static final String URL = "log.json";

    @Override
    public void escrever(String nomeUsuario, String nomeOperacao, Pedido pedido) {
        LocalDateTime agora = LocalDateTime.now();
        String data = agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = agora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        JSONObject logJson = new JSONObject();
        logJson.put("Nome_Usuario", nomeUsuario);
        logJson.put("Data", data);
        logJson.put("Hora", hora);
        logJson.put("Nome_de_Operacao", nomeOperacao);
        logJson.put("Codigo_Pedido", pedido.getCodigo());
        logJson.put("Nome_Cliente", pedido.getCliente().getNome());

        salvaJSONArquivo(logJson);
    }

    private void salvaJSONArquivo(JSONObject logJson){
        try {
            File file = new File(URL);
            if (!file.exists()) {
                file.createNewFile();
                JSONObject logsJson = new JSONObject();
                logsJson.put("logs", new JSONArray().put(logJson));

                try (FileWriter writer = new FileWriter(URL)) {
                    writer.write(logsJson.toString(4));
                }
            } else {
                try (FileReader reader = new FileReader(URL);
                     Scanner scanner = new Scanner(reader)) {

                    StringBuilder content = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        content.append(scanner.nextLine());
                    }

                    JSONObject logsJson = new JSONObject(content.toString());
                    logsJson.getJSONArray("logs").put(logJson);

                    try (FileWriter writer = new FileWriter(URL)) {
                        writer.write(logsJson.toString(4));
                    }
                }
            }
            System.out.println("Log adicionado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
