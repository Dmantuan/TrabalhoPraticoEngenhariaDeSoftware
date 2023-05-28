package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.DadoClima;
import models.EstacaoClimatica;
import models.Log;
import view.RegistrosView;

public class RegistrosPresenter implements IPainel {
    private ArrayList<DadoClima> dadosClima;
    private static RegistrosPresenter instance;
    private RegistrosView view;
    private DefaultTableModel tbClima;
    private static EstacaoClimatica estacaoClima = EstacaoClimatica.getInstance();
    
    private RegistrosPresenter(){
        
        this.dadosClima = new ArrayList<>();
        
        this.view = new RegistrosView();
        
        this.view.setLocation(16, 193+16);
        
        this.tbClima = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Data", "Temperatura", "Umidade", "Pressao"}
        );
        
        this.tbClima.setNumRows(0);
        
        this.view.getTb_registros().setModel(tbClima);
        
        this.view.getTb_registros().setEnabled(true);
        
        this.view.setVisible(true);
        
        this.view.getBtn_registros_remover().addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent ae) {
                    excluirRegistro();
                }
        });
        
    }
    
    public static RegistrosPresenter getInstance(){
        if(RegistrosPresenter.instance == null){
            RegistrosPresenter.instance = new RegistrosPresenter();
        }
        return RegistrosPresenter.instance;
    }
    
    public void excluirRegistro(){
        int rowIndex = this.view.getTb_registros().getSelectedRow();
        
        if (rowIndex >= 0 && rowIndex < this.tbClima.getRowCount()) {
            estacaoClima.removerDado(this.dadosClima.get(rowIndex));
            logRemover("remoção");
        }
        else{
            JOptionPane.showMessageDialog(view, "Nada selecionado");
        }
        
    }
    
    public void logRemover(String operacao){
        Log log = new Log(operacao);
        System.out.println(log);
    }
    
    @Override
    public void atualizar(DadoClima dadoClima, String tipo){
        if (tipo.equalsIgnoreCase("add")){
            this.dadosClima.add(dadoClima);
        }
        else{
            this.dadosClima.remove(dadoClima);
        }

        this.tbClima.setNumRows(0);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormated; 
 
        for(DadoClima dadoAux : this.dadosClima){
            dataFormated = LocalDate.parse(dadoAux.getData().format(formatter), formatter);
            this.tbClima.addRow(new Object[]{dataFormated.format(formatter), 
                String.valueOf(dadoAux.getTemperatura()), 
                String.valueOf(dadoAux.getUmidade()), 
                String.valueOf(dadoAux.getUmidade())
            });
        }
        
        
        this.view.getTb_registros().setModel(this.tbClima);
    }
    
    public RegistrosView getView(){
        return this.view;
    }
}
