package logModule;

import logModule.classes.DBLog;
import logModule.classes.JSONLog;
import logModule.classes.XMLLog;
import logModule.interfaces.ILog;

import java.util.HashMap;
import java.util.Map;

public final class RegistradorDeLogService {

    private static Map<String, ILog> tiposLog = new HashMap<>();
    private static ILog tipoLogSelecionado = null;

    static {
        tiposLog.put("DBLog", new DBLog());
        tiposLog.put("JSONLog", new JSONLog());
        tiposLog.put("XMLLog", new XMLLog());
    }

    private RegistradorDeLogService() {
        throw new UnsupportedOperationException("Nao e possivel instanciar uma classe utilitaria.");
    }

    public static ILog definirTipoLog(String tipoLog){
        if (tiposLog.containsKey(tipoLog) && tipoLogSelecionado == null){
            tipoLogSelecionado = tiposLog.get(tipoLog);
            return tiposLog.get(tipoLog);
        } else {
            throw new RuntimeException("Informe um tipo de log valido.");
        }
    }

    public static ILog getTipoLogSelecionado(){
        if (tipoLogSelecionado == null)
            throw new RuntimeException("Defina um tipo de log.");
        return tipoLogSelecionado;
    }
}
