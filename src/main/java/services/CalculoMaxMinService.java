/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import models.DadoClima;

/**
 *
 * @author daniel
 */
public class CalculoMaxMinService {
    
    private float maxTemperatura;
    private float maxUmidade;
    private float maxPressao;
    private float minTemperatura;
    private float minUmidade;
    private float minPressao;
    
    
    public void calcular(List<DadoClima> dadosClima){
        this.maxTemperatura = Float.NEGATIVE_INFINITY;
        this.maxUmidade = Float.NEGATIVE_INFINITY;
        this.maxPressao = Float.NEGATIVE_INFINITY;
        this.minTemperatura = Float.POSITIVE_INFINITY;
        this.minUmidade = Float.POSITIVE_INFINITY;
        this.minPressao = Float.POSITIVE_INFINITY;
        
        for (DadoClima dadoClima : dadosClima) {
            if (maxTemperatura < dadoClima.getTemperatura()) {
                maxTemperatura = dadoClima.getTemperatura();
            }
            if (minTemperatura > dadoClima.getTemperatura()) {
                minTemperatura = dadoClima.getTemperatura();
            }
            if (maxUmidade < dadoClima.getUmidade()) {
                maxUmidade = dadoClima.getUmidade();
            }
            if (minUmidade > dadoClima.getUmidade()) {
                minUmidade = dadoClima.getUmidade();
            }
            if (maxPressao < dadoClima.getPressao()) {
                maxPressao = dadoClima.getPressao();
            }
            if (minPressao > dadoClima.getPressao()) {
                minPressao = dadoClima.getPressao();
            }
        }
    }

    public float getMaxTemperatura() {
        return maxTemperatura;
    }

    public float getMaxUmidade() {
        return maxUmidade;
    }

    public float getMaxPressao() {
        return maxPressao;
    }

    public float getMinTemperatura() {
        return minTemperatura;
    }

    public float getMinUmidade() {
        return minUmidade;
    }

    public float getMinPressao() {
        return minPressao;
    }
}
