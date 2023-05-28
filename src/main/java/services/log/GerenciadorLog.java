package services.log;

import models.Log;

public class GerenciadorLog {
    public static void salvarLog(ILog tipoLog, Log log ){
        tipoLog.salvarLog(log);
    }
}
