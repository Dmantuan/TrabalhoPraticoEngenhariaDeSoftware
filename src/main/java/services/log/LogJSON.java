package services.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Log;

public class LogJSON implements ILog{
    
    private Gson gson;
    
    public LogJSON(){
        this.gson  = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @Override
    public void salvarLog(Log log){
        System.out.println(this.gson.toJson(log));
    }
}
