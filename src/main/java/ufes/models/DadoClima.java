package ufes.models;

import java.time.LocalDate;

public class DadoClima {

   private final float temperatura;
   private final float umidade;
   private final float pressao;
   private final LocalDate data;

   public DadoClima(float temperatura, float umidade, float pressao, LocalDate data) {
       this.temperatura = temperatura;
       this.umidade = umidade;
       this.pressao = pressao;
       this.data = data;
   }

   public float getTemperatura(){
       return this.temperatura;
   }
   
   public float getUmidade(){
       return this.umidade;
   }
   
   public float getPressao(){
       return this.pressao;
   }
   
   public LocalDate getData(){
       return this.data;
   }
}
