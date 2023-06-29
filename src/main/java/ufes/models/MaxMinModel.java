package ufes.models;

import java.time.LocalDate;

public class MaxMinModel {
    private LocalDate dataMedicao;
    private String tipo;
    private float valor;
    
    public MaxMinModel(String tipo, float valor, LocalDate dataMedicao){
        this.dataMedicao = dataMedicao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDate getDataMedicao() {
        return dataMedicao;
    }

    public String getTipo() {
        return tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setDataMedicao(LocalDate dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
