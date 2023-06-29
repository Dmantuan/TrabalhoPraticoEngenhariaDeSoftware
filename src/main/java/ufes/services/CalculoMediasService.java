/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufes.services;

import java.util.List;
import ufes.models.DadoClima;

/**
 *
 * @author daniel
 */
public class CalculoMediasService {
    private Float mediaTemperaturas;
    private Float mediaUmidades;
    private Float mediaPressoes;

    private Integer nro_registros;
    
    public void calcularMedias( List<DadoClima> dadosClima ){   
        float somaTemperaturas = 0;
        float somaUmidades = 0;
        float somaPressoes = 0;
        
        this.nro_registros = dadosClima.size();
        
        for (DadoClima dClima : dadosClima) {
            somaTemperaturas += dClima.getTemperatura();
            somaUmidades += dClima.getUmidade();
            somaPressoes += dClima.getPressao();
        }
        
        this.mediaPressoes = somaPressoes / this.nro_registros;
        this.mediaTemperaturas = somaTemperaturas / this.nro_registros;
        this.mediaUmidades = somaUmidades / this.nro_registros;
    }

    public Float getMediaTemperaturas() {
        return mediaTemperaturas;
    }

    public Float getMediaUmidades() {
        return mediaUmidades;
    }

    public Float getMediaPressoes() {
        return mediaPressoes;
    }

    public Integer getNro_registros() {
        return nro_registros;
    }
    
    
}
