package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ConfiguracaoView;

public class ConfiguracaoPresenter {
    private ConfiguracaoView view;
    
    public ConfiguracaoPresenter(){
        this.view = new ConfiguracaoView();
        this.view.setVisible(false);
        
        this.view.getCmbBox().removeAllItems();
        this.view.getCmbBox().addItem("XML");
        this.view.getCmbBox().addItem("JSON");
        
        this.view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setConfiguracao();
            }
        });
        
    }
    
    public void setConfiguracao(){
        
    }
    
    public void setVisible(){
        this.view.setVisible(true);
    }
    
}
