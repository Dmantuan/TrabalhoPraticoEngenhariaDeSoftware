package models;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import presenter.DadosMediosPresenter;
import presenter.DadosTempoPresenter;
import presenter.IPainel;
import presenter.MainPresenter;
import presenter.RegistrosPresenter;
import presenter.UltimaAtualizacaoPresenter;
import presenter.MaxMinPresenter;

public class EstacaoClimatica {
    
    private static EstacaoClimatica instancia;
    private DadosTempoPresenter principalFrame;
    private List<IPainel> paineis;

    private EstacaoClimatica() {
        paineis = new ArrayList<>();
        this.principalFrame = new DadosTempoPresenter(this);
        
        this.paineis.add(DadosMediosPresenter.getInstance());
        this.paineis.add(UltimaAtualizacaoPresenter.getInstance());
        this.paineis.add(MaxMinPresenter.getInstance());
        this.paineis.add(MainPresenter.getInstance());
        this.paineis.add(RegistrosPresenter.getInstance());
    }
    
    public static EstacaoClimatica getInstance(){
        if(EstacaoClimatica.instancia == null){
            EstacaoClimatica.instancia = new EstacaoClimatica();
        }
        return EstacaoClimatica.instancia;
    }
    
    public void registrarPainel(IPainel painel) {
        paineis.add(painel);
    }

    public void removerPainel(IPainel painel) {
        paineis.remove(painel);
    }

    public void addDado(float temperatura, float umidade, float pressao, LocalDate data) {
        DadoClima dadoClima = new DadoClima(temperatura, umidade, pressao, data);
        notificarPaineis(dadoClima, "add");
    }
    
    public void removerDado(DadoClima dadoClima){
        // addLogs(new LogModel("Removendo dado clima", dadosClima.get(rowIndex)));
        //logObserver.notificarGerenciadorLog(logModel.RemovendoDados(dadosClima.get(rowIndex)));
        notificarPaineis(dadoClima, "excluir");
    }

    private void notificarPaineis(DadoClima dadoClima, String tipo) {
        for (IPainel painel : paineis) {
            painel.atualizar(dadoClima, tipo);
        }
    }
}
