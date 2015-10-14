/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.rbnb.sapi.ChannelMap;
import com.rbnb.sapi.SAPIException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static source.RBNBBase.logger;

/**
 *
 * @author Natus
 */
public class SourceReader extends Thread{
    private Thread t;
    ChannelMap m;
    int[] channel;
    File fileName;
    BufferedReader b;
    int dataLineNumber;
    int infoLineNumber;
    int timeIndex;
    Sensor s;
    
    SourceReader(ChannelMap m, Sensor s){
        this.m = m;
        //this.channel = 0;
        this.fileName = s.f;
        this.infoLineNumber = s.infoLineNumber;
        this.dataLineNumber = s.dataLineNumber;
        this.s = s;
        timeIndex = s.timeStampColumn - 1;
  
        
    }
    public void run(){

                try {
			b = new BufferedReader(new FileReader(fileName));
                        //get infoLine
			String line = null;
                        String infoLine = null;
			int counter = 1;
			for (counter = 0 ; counter <= this.infoLineNumber - 1; counter++){
                            infoLine = b.readLine();
                        }
                        System.out.println(infoLine);
                     String fields[] = infoLine.split(s.d.toString());
                     channel = new int[fields.length];
                     
                     for(int i = 0; i<fields.length; i++){
                         if(!ignore(i)){
                             try {
                                 channel[i] = m.Add(fields[i]);
                                 m.PutMime(channel[i], "application/octet-stream");
                             } catch (SAPIException ex) {
                                 Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                        
                         for(int j = counter; j < s.dataLineNumber;j++){
                             b.readLine();
                         }
                        
                        while(true){
                            line = b.readLine();
                            System.out.println(line);
                            if(line==null){
                                //wait
                            }else{
                                String[] lineSplit = line.split(s.d.toString());
                                System.out.println(s.d.toString());
                                System.out.println(lineSplit[timeIndex]);
                                double time = getRbnbTimestamp(lineSplit[timeIndex]);
                                
                                for(int j = 0; j<lineSplit.length; j++){
                                    m.PutTime(time, 1);
                                    if(j != timeIndex){
                                        try {
                                            m.PutDataAsString(channel[j],lineSplit[j]);
                                        } catch (SAPIException ex) {
                                            Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                    
                                
                            }
                        }
                     }
			//return line;
                }catch (IOException e) {
			logger.severe("Loggernet file doesn't exist");
			//return null;
		}

        
    }
   
    

   
    private boolean ignore(int i){
        return false;
    }
    
    public static double getRbnbTimestamp(String loggernetDate) {
		/*! @note ISORbnbTime uses ISO8601 timestamp, e.g. 2003-08-12T19:21:22.30095 */
		/*! @note from loggernet: "2007-11-12 07:30:00" */
		String[] loggernetDateTokens = loggernetDate.split(" ");
		StringBuffer retval = new StringBuffer();
		
		retval.append(loggernetDateTokens[0]);
		retval.append("T");
		retval.append(loggernetDateTokens[1]);
		// time
		retval.append(".00000");
		String iso8601String = retval.toString();
                System.out.println(iso8601String);
		//logger.fine("ISO8601:" + iso8601String);
		
		ISOtoRbnbTime rbnbTimeConvert = new ISOtoRbnbTime(iso8601String);
		
		System.out.println ("original date string = " + loggernetDate);
		System.out.println ("ISO date string = " + iso8601String);
		
		return rbnbTimeConvert.getValue();
	}
        
}