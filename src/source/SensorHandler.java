/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.rbnb.sapi.ChannelMap;
import com.rbnb.sapi.SAPIException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natus
 */
public class SensorHandler {
    static ChannelMap ch;
    SensorHandler(){
       ch = new ChannelMap();
       
    }
    
    public static void  SHStartUp(){
        
        
    }
    public static void SHShutDown(){
    }

    public static void runSensor(Sensor s){  
       
        ch.PutMime(s.channelNumber,"application/octet-stream");
        
        SourceReader sr = new SourceReader(ch,s);
        sr.start();
        
    }
}
