package ufes.services.log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import ufes.models.Log;
import ufes.services.arquivo.EscritaEmArquivoXML;
import ufes.services.arquivo.GerenciadorEscritaArquivo;

public class LogXML implements ILog{
    
    public XStream xStream;
    private EscritaEmArquivoXML arquivo;
    
    public LogXML(){
        this.xStream = new XStream(new DomDriver());
        xStream.alias("dadoClima", Log.class);
        arquivo = EscritaEmArquivoXML.getInstancia();
    }
    
    @Override
    public void salvarLog(Log log){
        GerenciadorEscritaArquivo.escrever(arquivo, xStream.toXML(log));
    }
}
