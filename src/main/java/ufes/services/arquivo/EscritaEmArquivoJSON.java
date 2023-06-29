package ufes.services.arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class EscritaEmArquivoJSON implements IArquivo{
    private static EscritaEmArquivoJSON instancia;
    String currentDirectory = System.getProperty("user.dir");
    private final String filePath =  currentDirectory + "/src/main/java/ufes/services/arquivo/LogJSON.json";
    
    private FileWriter fw;
    private File file;
    
    private EscritaEmArquivoJSON(){
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
    
    public static EscritaEmArquivoJSON getInstancia(){
        if(instancia == null){
            EscritaEmArquivoJSON.instancia = new EscritaEmArquivoJSON();
        }
        return EscritaEmArquivoJSON.instancia;
    }
    
    @Override
    public void escreverArquivo(String log){

        try {
            this.fw = new FileWriter(file, true);
            
            // Verifica se o arquivo está vazio
            if (file.length() != 0) {
                fw.write(",\n");
            }
            
            fw.write(log);
            fw.close();
        } catch (IOException ioEx) {
            JOptionPane.showMessageDialog(null, ioEx);
        }


        
    }
}
