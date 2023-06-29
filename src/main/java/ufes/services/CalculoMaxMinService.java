package ufes.services;

import java.time.LocalDate;
import java.util.List;
import ufes.models.DadoClima;
import ufes.models.MaxMinModel;

public class CalculoMaxMinService {
    
    private final MaxMinModel maxTemperatura;
    private final MaxMinModel maxUmidade;
    private final MaxMinModel maxPressao;
    private final MaxMinModel minTemperatura;
    private final MaxMinModel minUmidade;
    private final MaxMinModel minPressao;
    
    public CalculoMaxMinService(){
        this.maxTemperatura = new MaxMinModel("maxTemperatura", Float.NEGATIVE_INFINITY, LocalDate.now());
        this.maxUmidade = new MaxMinModel("maxUmidade", Float.NEGATIVE_INFINITY, LocalDate.now());
        this.maxPressao = new MaxMinModel("maxPressao", Float.NEGATIVE_INFINITY, LocalDate.now());
        this.minTemperatura = new MaxMinModel("minTemperatura", Float.NEGATIVE_INFINITY, LocalDate.now());
        this.minUmidade = new MaxMinModel("minUmidade", Float.NEGATIVE_INFINITY, LocalDate.now());
        this.minPressao = new MaxMinModel("minPressao", Float.NEGATIVE_INFINITY, LocalDate.now());
    }
    
    
    public void calcular(List<DadoClima> dadosClima){
        this.maxTemperatura.setValor(Float.NEGATIVE_INFINITY);
        this.maxUmidade.setValor(Float.NEGATIVE_INFINITY);
        this.maxPressao.setValor(Float.NEGATIVE_INFINITY);
        this.minTemperatura.setValor(Float.POSITIVE_INFINITY);
        this.minUmidade.setValor(Float.POSITIVE_INFINITY);
        this.minPressao.setValor(Float.POSITIVE_INFINITY);
        
        for (DadoClima dadoClima : dadosClima) {
            if (maxTemperatura.getValor() < dadoClima.getTemperatura()) {
                maxTemperatura.setValor(dadoClima.getTemperatura());
                maxTemperatura.setDataMedicao(dadoClima.getData());
            }
            if (minTemperatura.getValor() > dadoClima.getTemperatura()) {
                minTemperatura.setValor(dadoClima.getTemperatura());
                minTemperatura.setDataMedicao(dadoClima.getData());
            }
            if (maxUmidade.getValor() < dadoClima.getUmidade()) {
                maxUmidade.setValor(dadoClima.getUmidade());
                maxUmidade.setDataMedicao(dadoClima.getData());
            }
            if (minUmidade.getValor() > dadoClima.getUmidade()) {
                minUmidade.setValor(dadoClima.getUmidade());
                minUmidade.setDataMedicao(dadoClima.getData());
            }
            if (maxPressao.getValor() < dadoClima.getPressao()) {
                maxPressao.setValor(dadoClima.getPressao());
                maxPressao.setDataMedicao(dadoClima.getData());
            }
            if (minPressao.getValor() > dadoClima.getPressao()) {
                minPressao.setValor(dadoClima.getPressao());
                minPressao.setDataMedicao(dadoClima.getData());
            }
        }
    }

    public MaxMinModel getMaxTemperatura() {
        return maxTemperatura;
    }

    public MaxMinModel getMaxUmidade() {
        return maxUmidade;
    }

    public  MaxMinModel getMaxPressao() {
        return maxPressao;
    }

    public  MaxMinModel getMinTemperatura() {
        return minTemperatura;
    }

    public  MaxMinModel getMinUmidade() {
        return minUmidade;
    }

    public  MaxMinModel getMinPressao() {
        return minPressao;
    }
}
