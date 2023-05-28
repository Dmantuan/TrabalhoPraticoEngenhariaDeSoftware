package presenter;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import models.DadoClima;
import view.MainView;
import models.EstacaoClimatica;

public class MainPresenter implements IPainel{

    private static MainView view;
    private EstacaoClimatica estacaoClimatica;
    private List<DadoClima> dadosClima;
    private static MainPresenter instance;
    
    private MainPresenter(){
        MainPresenter.view = new MainView();
        
        this.dadosClima = new ArrayList();
        
//        MainPresenter.view.setResizable(false);
//        MainPresenter.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//        graphicsDevice.setFullScreenWindow(MainPresenter.view);
    }
    
    public static MainPresenter getInstance(){
        if(MainPresenter.instance == null){
            MainPresenter.instance = new MainPresenter();
        }
        return MainPresenter.instance;
    }
    
    public void MainFramePresenter() {
        this.estacaoClimatica = EstacaoClimatica.getInstance();
        
        ConfiguracaoPresenter configurarPresenter = new ConfiguracaoPresenter();
        DadosTempoPresenter dadosTempoPresenter = new DadosTempoPresenter(this.estacaoClimatica);
        UltimaAtualizacaoPresenter ultimaAtualiacaoPresenter = UltimaAtualizacaoPresenter.getInstance();
        DadosMediosPresenter dadosMediosPresenter = DadosMediosPresenter.getInstance();
        RegistrosPresenter registrosPresenter = RegistrosPresenter.getInstance();
        MaxMinPresenter maxMinPresenter = MaxMinPresenter.getInstance();
        
        MainPresenter.view.getDesktopPanel().add(dadosTempoPresenter.getDadosTempoView());
        MainPresenter.view.getDesktopPanel().add(dadosMediosPresenter.getView());
        MainPresenter.view.getDesktopPanel().add(ultimaAtualiacaoPresenter.getView());
        MainPresenter.view.getDesktopPanel().add(registrosPresenter.getView());
        MainPresenter.view.getDesktopPanel().add(maxMinPresenter.getView());
        
        MainPresenter.view.getBtn_configurar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                configurarPresenter.setVisible();
            }
        });
        
        MainPresenter.view.getTx_qtd_registros().setText("0");
        MainPresenter.view.setVisible(true);
    }
    
    @Override
    public void atualizar(DadoClima dadoClima, String tipo){
        
        if(tipo.equalsIgnoreCase("add")){
            this.dadosClima.add(dadoClima);
        }
        else{
            this.dadosClima.remove(dadoClima);
        }

        MainPresenter.view.getTx_qtd_registros().setText(String.valueOf(this.dadosClima.size()));
    }
    
    public EstacaoClimatica getEstacaoClimatica(){
        return this.estacaoClimatica;
    }
    
    public static MainView getView(){
        return MainPresenter.view;
    }
}
