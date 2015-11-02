
package source;

import com.rbnb.sapi.ChannelMap;
import com.rbnb.sapi.SAPIException;
import com.rbnb.sapi.Source;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import static source.RBNBBase.logger;

/*
 * Source Reader
 * @author Nathan Castlehow (21318883)
 * @version 1.0
 * Responsible for polling text files for new data
 * Some code adapted from Loggernet Src
 * Available at http://www.dataturbine.org/
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
    int lineCount;
    Sensor s;
    Source RBS;
    boolean running;
    
    
    /**
     * Constructor for Source Reader
     * @param RBS RBNB source
     * @param m ChannelMap
     * @param s sensor 
     */
    SourceReader(Source RBS, Sensor s){
        this.m = new ChannelMap();
        //this.channel = 0;
        this.fileName = s.f;
        this.infoLineNumber = s.infoLineNumber - 1;
        this.dataLineNumber = s.dataLineNumber - 1;
        this.s = s;
        this.RBS = RBS;
        timeIndex = s.timeStampColumn - 1;
        running = true;
        lineCount = dataLineNumber;
        
        
    }
    
    /**
     * Run
     * run thread so it continues polling all the time
     */
    public void run(){
        try {
                b = new BufferedReader(new FileReader(fileName));
                //get infoLine
                String line = null;
                String infoLine = null;
                int counter = 1;
                for (counter = 0 ; counter <= this.infoLineNumber; counter++){
                    infoLine = b.readLine();
                }
                System.out.println(infoLine);
                String fields[] = infoLine.split(s.d.toString());
                channel = new int[fields.length];

                for(int i = 0; i<fields.length; i++){
                    m.PutTime(0.0 + i/100,0.0);
                 if(!ignore(i)){
                     //System.out.println("field" + i);
                     //System.out.println("Length" + fields.length);
                    try {
                        channel[i] = m.Add(s.name + "/" + fields[i].replace("\"",""));
                        m.PutMime(channel[i], "application/octet-stream");
                        System.out.println("Channel Added");
                    } catch (SAPIException ex) {
                         Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
                }

                for(int j = counter; j < s.dataLineNumber;j++){
                     b.readLine();
                 }

                while(running){
                    
                    line = b.readLine();
                    
                    if(line==null){
                        try {
                        sleep(30000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }else{
                      
                        String[] lineSplit = line.split(s.d.toString());
                        TimeZone tz = TimeZone.getDefault();
                        double time = getRbnbTimestamp(lineSplit[timeIndex].replace("\"","")) + ((double) tz.getRawOffset() / 1000.0);
                        System.out.println(time);
                        
                        int x = 0;
                        m.PutTime(time,0.0);
                        float [] [] array = new float[lineSplit.length][1];
                        for(String split : lineSplit){
                            if(!ignore(x)) {
                                
                                    array[x][0] = Float.parseFloat(split.replace("\"",""));
                                    //channel[jj]
                                    //System.out.println(x + ":" + split
           
       
                            }
                            x++;
                        }
                        for (x = 0; x < channel.length; x++) {
                            try {
                                m.PutDataAsFloat32(channel[x],array[x]);
                            } catch (SAPIException ex) {
                                Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        try {
                            System.out.println("Flush" + RBS.Flush(m));
                            lineCount++;
                        } catch (SAPIException ex) {
                            Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }

                    }
                }
             catch (IOException e) {
                logger.severe("Loggernet file doesn't exist");
                //return null;
        }
        
        

        
    }
   
    public void stopSR(){
        running = false;
    }
    
    /**
    * ignore
    * checks whether column should be ignored such as timestamp column
    * @param i column number
    * @return whether the column should be ignored
    */
    private boolean ignore(int i){
        return i==timeIndex;
    }
    
    private void shutDown(){
    
            BufferedWriter b;
            try {
                b = new BufferedWriter(new FileWriter(s.name + ".sensor"));
                b.write(s.toString() + "\n LineCount:" + lineCount);
            } catch (IOException ex) {
                Logger.getLogger(SourceReader.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        
        
    }
    
    /**
     * getRbnbTimeStamp
     * Converts timestamp into double
     * @param loggernetDate
     * @return timestamp as double
     * From LoggerNetSrc
     */
    private static double getRbnbTimestamp(String loggernetDate) {
		/*! @note ISORbnbTime uses ISO8601 timestamp, e.g. 2003-08-12T19:21:22.30095 */
		/*! @note from loggernet: "2007-11-12 07:30:00" */
		String[] loggernetDateTokens = loggernetDate.split(" ");
                System.out.println("length" + loggernetDateTokens.length);
		StringBuffer retval = new StringBuffer();
		
		retval.append(loggernetDateTokens[0]);
		retval.append("T");
		retval.append(loggernetDateTokens[1]);
		// time
		retval.append(".00000");
		String iso8601String = retval.toString();
                //System.out.println(iso8601String);
		//logger.fine("ISO8601:" + iso8601String);
		
		ISOtoRbnbTime rbnbTimeConvert = new ISOtoRbnbTime(iso8601String);
		
		System.out.println ("original date string = " + loggernetDate);
                System.out.println ("ISO date string = " + iso8601String);
		
		return rbnbTimeConvert.getValue();
	}
        
}