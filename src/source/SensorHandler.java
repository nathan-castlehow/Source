/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.rbnb.sapi.ChannelMap;
import com.rbnb.sapi.SAPIException;
import com.rbnb.sapi.Source;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natus
 */
public class SensorHandler {
    static ChannelMap ch;
    static Source RBS;
    static int sensors;
    SensorHandler(Source s){
       ch = new ChannelMap();
       RBS = s;
       sensors = 0;
       System.out.println("RBS" + RBS);
       
       
    }
    
    public static void  SHStartUp(){
        
        
    }
    public static void SHShutDown(){
    }

    public static void runSensor(Sensor s){  
       
        //ch.PutMime(s.channelNumber,"application/octet-stream");
//        sensors ++;
//        if(sensors ==1){
//            CMapFlush CMF = new CMapFlush(RBS,ch);
//            CMF.start();
//        }
        
        SourceReader sr = new SourceReader(RBS,ch,s);
        sr.start();
        
    }
}
