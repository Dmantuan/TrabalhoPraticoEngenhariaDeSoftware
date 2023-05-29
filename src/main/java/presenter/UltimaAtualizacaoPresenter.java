package presenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import models.DadoClima;
import services.localDate.FormatadorLocalDate;
import view.UltimaAtualizacaoView;


public class UltimaAtualizacaoPresenter implements IPainel {
    
    private List<DadoClima> dadosClima;
    private static UltimaAtualizacaoPresenter instance;
    private static UltimaAtualizacaoView view;
    
    private UltimaAtualizacaoPresenter(){
        UltimaAtualizacaoPresenter.view = new UltimaAtualizacaoView();
        
        this.dadosClima = new ArrayList<>();
        
        UltimaAtualizacaoPresenter.view.getTx_atualizacao_Data().setText("");
        UltimaAtualizacaoPresenter.view.getTx_atualizacao_Pressao().setText("");
        UltimaAtualizacaoPresenter.view.getTx_atualizacao_Temperatura().setText("");
        UltimaAtualizacaoPresenter.view.getTx_atualizacao_Umidade().setText("");
        
        UltimaAtualizacaoPresenter.view.setLocation(243+32, 8);
        
        UltimaAtualizacaoPresenter.view.setVisible(true);
    }
    
    public static UltimaAtualizacaoPresenter getInstance(){
        if(UltimaAtualizacaoPresenter.instance == null){
            UltimaAtualizacaoPresenter.instance = new UltimaAtualizacaoPresenter();
        }
        return UltimaAtualizacaoPresenter.instance;
    }
    
    @Override
    public void atualizar(DadoClima dadoClima, String tipo){
        if(tipo.equalsIgnoreCase("Add")){
            this.dadosClima.add(dadoClima);
        }
        else{
            this.dadosClima.remove(dadoClima);
        }
        
        
        if(this.dadosClima.isEmpty()){
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Data().setText("");
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Pressao().setText(String.valueOf(""));
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Temperatura().setText(String.valueOf(""));
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Umidade().setText(String.valueOf(""));
        }
        else{
            DadoClima auxDadoClima = this.dadosClima.get(this.dadosClima.size()-1);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataFormated = FormatadorLocalDate.formatar(auxDadoClima.getData().format(formatter));

            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Data().setText(dataFormated.format(formatter));
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Pressao().setText(String.valueOf(auxDadoClima.getPressao()));
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Temperatura().setText(String.valueOf(auxDadoClima.getTemperatura()));
            UltimaAtualizacaoPresenter.view.getTx_atualizacao_Umidade().setText(String.valueOf(auxDadoClima.getUmidade()));
            
        } 
    }
    
    public UltimaAtualizacaoView getView(){
        return UltimaAtualizacaoPresenter.view;
    }
    
}
