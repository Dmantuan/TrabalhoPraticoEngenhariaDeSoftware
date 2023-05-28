package services.log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Log;

public class LogXML implements ILog{
    
    public XStream xStream;
    
    public LogXML(){
        this.xStream = new XStream(new DomDriver());
        xStream.alias("dadoClima", Log.class);
    }
    
    @Override
    public void salvarLog(Log log){
        System.out.println(xStream.toXML(log));
        
    }
}
