package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import models.EstacaoClimatica;
import models.Log;
import services.log.GerenciadorLog;
import services.log.ILog;
import services.log.LogJSON;
import services.log.LogXML;
import view.DadosTempoView;

public class DadosTempoPresenter{

    private DadosTempoView view;
    private final EstacaoClimatica estacaoClimatica;
    private ILog tipoLog;
    private LogXML logXML = new LogXML();
    private LogJSON logJSON = new LogJSON();
    
    public DadosTempoPresenter(EstacaoClimatica estacaoClimatica) {              
        this.view = new DadosTempoView();
        
//        int parentWidth = MainPresenter.getView().getWidth();
//        int desiredWidth = (int) (parentWidth * (50 / 100.0));
//        
//        this.view.setSize(desiredWidth, view.getHeight());
//        
//        this.view.getBtn_incluir().setSize(d);
        
        this.estacaoClimatica = estacaoClimatica;
        
        this.view.setLocation(16, 8);
        
        this.view.setVisible(true);
        
        this.view.getBtn_incluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                float umidade = Float.parseFloat(view.getTx_umidade().getText());
                float temperatura = Float.parseFloat(view.getTx_temperatura().getText());
                float pressao = Float.parseFloat(view.getTx_pressao().getText());                
                String data = view.getTx_data().getText();
                
                incluirDado(temperatura, umidade, pressao, data);
                reinicarOsValoresDoPainel();
            }
        }); 
    }

    private void incluirDado(float temperatura, float umidade, float pressao, String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormated = LocalDate.parse(data, formatter);

        if (dataFormated.isBefore(LocalDate.now())) {
            this.estacaoClimatica.addDado(temperatura, umidade, pressao, dataFormated);
            logAdd("inclusão");
        } else {
            JOptionPane.showMessageDialog(null, "A data está inválida");
        }
    }

    public DadosTempoView getDadosTempoView() {
        return this.view;
    }
    
    public void logAdd(String operacao){
        Log log = new Log(operacao);
        tipoLog = new LogXML();
        GerenciadorLog.salvarLog(tipoLog, log);
    }
    
    private void reinicarOsValoresDoPainel(){
        this.view.getTx_data().setText("");
        this.view.getTx_pressao().setText("");
        this.view.getTx_temperatura().setText("");
        this.view.getTx_umidade().setText("");
    }
}
