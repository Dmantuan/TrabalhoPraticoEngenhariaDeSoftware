package models;

import java.time.LocalDateTime;

public class Log {
    private final LocalDateTime data;
    private final String operacao;
    
    public Log(String operacao){
        this.data = LocalDateTime.now();
        this.operacao = operacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getOperacao() {
        return operacao;
    }

}
