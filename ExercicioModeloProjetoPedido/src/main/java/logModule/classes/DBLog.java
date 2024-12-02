package logModule.classes;

import logModule.interfaces.ILog;

public class DBLog implements ILog {
    @Override
    public void escrever(String mensagem) {
        System.out.println("Mensagem de calculo com DBLog");
    }
}
