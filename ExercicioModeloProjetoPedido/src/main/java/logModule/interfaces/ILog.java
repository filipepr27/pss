package logModule.interfaces;

import classes.Pedido;

public interface ILog {
    public void escrever(String mensagem, Pedido pedido);
}
