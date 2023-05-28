package presenter;

import java.util.ArrayList;
import java.util.List;
import models.DadoClima;
import services.CalculoMediasService;
import view.DadosMediosView;

public class DadosMediosPresenter implements IPainel {
    private List<DadoClima> dadosClima;
    private static DadosMediosPresenter instance;
    private final DadosMediosView view;
    private CalculoMediasService calculoMediasService;
    
    private DadosMediosPresenter(){
        this.dadosClima = new ArrayList<>();
        this.calculoMediasService = new CalculoMediasService();
        
        this.view = new DadosMediosView();
        this.view.setVisible(true);
        
        this.view.getTx_medios_nroRegistros().setText("None");
        this.view.getTx_medios_pressao().setText("None");
        this.view.getTx_medios_temperatura().setText("None");
        this.view.getTx_medios_umidade().setText("None");
        
        System.out.println(UltimaAtualizacaoPresenter.getTamanho());
        //this.view.setLocation(UltimaAtualizacaoPresenter.getTamanho().get(0).intValue() + 8, UltimaAtualizacaoPresenter.getTamanho().get(1).intValue() + 8);
    }
    
    public static DadosMediosPresenter getInstance(){
        if(DadosMediosPresenter.instance == null){
            DadosMediosPresenter.instance = new DadosMediosPresenter();
        }
        return DadosMediosPresenter.instance;
    }
    
    @Override
    public void atualizar(DadoClima dadoClima, String tipo) {
        
        if(tipo.equalsIgnoreCase("add")){
            this.dadosClima.add(dadoClima);
        }
        else{
            this.dadosClima.remove(dadoClima);
        }
        
        
        calculoMediasService.calcularMedias(this.dadosClima);
        
        this.view.getTx_medios_nroRegistros().setText(String.valueOf(calculoMediasService.getNro_registros()));
        this.view.getTx_medios_pressao().setText(String.valueOf(calculoMediasService.getMediaPressoes()));
        this.view.getTx_medios_temperatura().setText(String.valueOf(calculoMediasService.getMediaTemperaturas()));
        this.view.getTx_medios_umidade().setText(String.valueOf(calculoMediasService.getMediaUmidades()));
    }
    
    public DadosMediosView getView(){   
        return this.view;
    }   
}
