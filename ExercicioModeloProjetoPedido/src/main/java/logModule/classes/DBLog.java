package logModule.classes;

import classes.Pedido;
import bancoDeDados.LogSQLite;
import java.text.SimpleDateFormat;
import java.util.Date;
import logModule.interfaces.ILog;

public class DBLog implements ILog {
    @Override
    public void escrever(String nomeUsuario, String nomeOperacao, Pedido pedido) {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        int codigoPedido = pedido.getCodigo();
        String nomeCliente = pedido.getCliente().getNome();
        String mensagemLog = String.format("NOME_USUARIO: %s; Data (DD/MM/YYYY): %s; Hora (HH:MM:SS): %s; " +
                "codigo_pedido: %s; Nome de Operação: %s; Nome_Cliente: %s",nomeUsuario, data, hora, 
                codigoPedido, nomeOperacao, nomeCliente);

        salvarLogBD(mensagemLog, nomeUsuario);
    }

    private void salvarLogBD(String mensagemLog, String nomeUsuario){
        LogSQLite logSQLite = new LogSQLite();
        logSQLite.inserir(mensagemLog,nomeUsuario);
    }
}
