/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.rbnb.sapi.ChannelMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static source.RBNBBase.logger;

/**
 *
 * @author Natus
 */
public class SourceReader extends Thread{
    private Thread t;
    ChannelMap m;
    int channel;
    File fileName;
    BufferedReader b;
    int DataLineNumber;
    
    SourceReader(ChannelMap m,int channel,File fileName,int lineNumber){
        this.m = m;
        this.channel = channel;
        this.fileName = fileName;
        DataLineNumber = lineNumber;
        
    }
    public void run(){

            try {
			b = new BufferedReader(new FileReader(fileName));
		}
		catch (FileNotFoundException e) {
			logger.severe("Loggernet file doesn't exist");
			//return null;
		}
		
		try {

			String line = null;
			
			int counter = 0;
			for (counter = 0 ; counter <= this.DataLineNumber; counter++)
				line = b.readLine();
			
			//return line;
		}
		catch (IOException e) {
			e.printStackTrace();
			//return null;
		}

        
    }
   
    
}
