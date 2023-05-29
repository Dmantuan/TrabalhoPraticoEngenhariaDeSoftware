package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import models.EstacaoClimatica;
import models.Log;
import services.localDate.FormatadorLocalDate;
import services.log.GerenciadorLog;
import view.DadosTempoView;

public class DadosTempoPresenter{

    private DadosTempoView view;
    private final EstacaoClimatica estacaoClimatica;
    
    public DadosTempoPresenter(EstacaoClimatica estacaoClimatica) {              
        this.view = new DadosTempoView();
        
        this.estacaoClimatica = estacaoClimatica;
        
        this.view.setLocation(16, 8);
        
        this.view.setVisible(true);
        
        this.view.getBtn_incluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    float umidade = Float.parseFloat(view.getTx_umidade().getText());
                    float temperatura = Float.parseFloat(view.getTx_temperatura().getText());
                    float pressao = Float.parseFloat(view.getTx_pressao().getText());                
                    String data = view.getTx_data().getText();
                    incluirDado(temperatura, umidade, pressao, data);
                    reinicarOsValoresDoPainel();
                }
                catch(DateTimeParseException exParse){
                    JOptionPane.showMessageDialog(view, "A data: '" + exParse.getParsedString() + "' nao esta correta");
                }
                catch(DateTimeException ex){
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
                catch(NumberFormatException numberEx){
                    JOptionPane.showMessageDialog(view, "os dados nao puderam ser comutador, favor reveja eles");
                }
                
            }
        }); 
    }

    private void incluirDado(float temperatura, float umidade, float pressao, String data) {
        LocalDate dataFormated = FormatadorLocalDate.formatar(data);

        if (dataFormated.isBefore(LocalDate.now())) {
            this.estacaoClimatica.addDado(temperatura, umidade, pressao, dataFormated);
            logAdd("inclusão");
        } else {
            JOptionPane.showMessageDialog(view, "A data está inválida");
        }
    }

    public DadosTempoView getDadosTempoView() {
        return this.view;
    }
    
    public void logAdd(String operacao){
        Log log = new Log(operacao);
        GerenciadorLog.salvarLog(ConfiguracaoPresenter.getTipoLog(), log);
    }
    
    private void reinicarOsValoresDoPainel(){
        this.view.getTx_data().setText("");
        this.view.getTx_pressao().setText("");
        this.view.getTx_temperatura().setText("");
        this.view.getTx_umidade().setText("");
    }
}
