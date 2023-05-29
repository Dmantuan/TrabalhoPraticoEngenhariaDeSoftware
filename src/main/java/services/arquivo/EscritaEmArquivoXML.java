package services.arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class EscritaEmArquivoXML implements IArquivo{
    private static EscritaEmArquivoXML instancia;
    private final String filePath = "/home/daniel/NetBeansProjects/Observer/src/main/java/services/arquivo/LogXML.xml";
    
    private FileWriter fw;
    private File file;
    
    private EscritaEmArquivoXML(){
        try{
            this.file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch(IOException exIO){
            JOptionPane.showMessageDialog(null, "Nao foi possivel ler o arquivo");
        }
    }
    
    public static EscritaEmArquivoXML getInstancia(){
        if(instancia == null){
            EscritaEmArquivoXML.instancia = new EscritaEmArquivoXML();
        }
        return EscritaEmArquivoXML.instancia;
    }
    
    @Override
    public void escreverArquivo(String log){
         try{
            this.fw = new FileWriter(file, true);
            fw.write(log);
            fw.write("\n");
            fw.close();
        }
        catch(IOException ioEx){
            JOptionPane.showMessageDialog(null, ioEx);
        }
        
    }
}
