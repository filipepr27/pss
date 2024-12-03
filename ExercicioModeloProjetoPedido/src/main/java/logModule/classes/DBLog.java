package logModule.classes;

import classes.Pedido;
import bancoDeDados.Scripts;
import java.text.SimpleDateFormat;
import java.util.Date;
import logModule.interfaces.ILog;
import services.UsuarioLogadoService;

public class DBLog implements ILog {
    @Override
    public void escrever(String nomeUsuario, Pedido pedido) {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        int codigoPedido = pedido.getCodigo();
        String nomeOperacao = "Calculo do valor total do pedido ( getDescontoConcedidoValorPedido )";
        String nomeCliente = pedido.getCliente().getNome();
        String log = String.format("NOME_USUARIO: %s; Data (DD/MM/YYYY): %s; Hora (HH:MM:SS): %s; " +
                "codigo_pedido: %s; Nome de Operação: %s; Nome_Cliente: %s",nomeUsuario, data, hora, 
                codigoPedido, nomeOperacao, nomeCliente);
        Scripts script = new Scripts();
        script.inserir(log,nomeUsuario);
        
        
       /*System.out.println(log);*/
        

    }
}
