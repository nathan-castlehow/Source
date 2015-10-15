/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.rbnb.sapi.Source;
import com.rbnb.sapi.ChannelMap;
import com.rbnb.sapi.SAPIException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natus
 */
public class CMapFlush extends Thread {
    Source sapiSrc;
    ChannelMap m;
    Thread t;
    
    public CMapFlush(Source sapiSrc, ChannelMap m){
        this.m = m;
        this.sapiSrc = sapiSrc;
       // System.out.println("Map flush initialized");
        System.out.println(sapiSrc);
    }
    
    /**private void postData(double[] someData) throws SAPIException {
		// put data onto the ring buffer - skips first element, which is the rbnb timestamp
		this.cmap.PutTime(someData[0], 0.0);
		for(int i=1; i<someData.length; i++) {
			double[] dataTmp = new double[1];
			dataTmp[0] = someData[i];
			String[] varChannels = channels;
			this.cmap.PutDataAsFloat64(cmap.GetIndex(varChannels[i]), dataTmp);
			System.out.println("Posted data:" + dataTmp[0] + " into channel: " + varChannels[i] + " : " + cmap.GetIndex(varChannels[i]));
		}				
		
	}**/
public void run(){
    while (true){
            try {
                sapiSrc.Flush(m);
              //  System.out.println("Flush");
                sleep(1);
            } catch (SAPIException ex) {
                Logger.getLogger(CMapFlush.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(CMapFlush.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
}
    
   /**  public void start(){
        t = new Thread(this,"T1");
        t.start();
    }
}**/



