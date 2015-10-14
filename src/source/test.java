/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.rbnb.sapi.SAPIException;
import com.rbnb.sapi.Source;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natus
 */
public class test {
    static Source sapiSrc;
    public static void main(){
        String serverName = "130.95.176.141:3333";
        String rbnbClientName = "Marine Source";
        int rbnbCacheSize = 1024;
        int rbnbArchiveSize = 1024;
        if (0 < rbnbArchiveSize) {
			sapiSrc = new Source (rbnbCacheSize, "append", rbnbArchiveSize);
		} else {
			sapiSrc = new Source(rbnbCacheSize, "none", 0);
		}
        try {
            //this.cmap = generateCmap();
            sapiSrc.OpenRBNBConnection(serverName, rbnbClientName);
        } catch (SAPIException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
