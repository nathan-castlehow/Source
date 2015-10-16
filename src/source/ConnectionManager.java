package source;



import com.rbnb.sapi.ChannelMap;
import com.rbnb.sapi.SAPIException;
import com.rbnb.sapi.Source;
import static source.RBNBBase.*;
import java.io.IOException;
import java.util.logging.Logger;
import source.Src;

/**
 * This file takes care of setting up a connection to the dataturbine server
 * @author Natus
 * @version 1.0
 */
public class ConnectionManager {
    private Source sapiSrc = null;
	
    protected ChannelMap cmap = null;
    protected String[] channels = null;
    protected String[] units = null;
    private String serverName;
    private String rbnbClientName;
    private int rbnbCacheSize;
    private int rbnbArchiveSize;
    private int initialised = 0;
    private int connected;
    //private Logger logger;

   /**
    * ConnectionManager
    * Sets up structure for connection to Dataturbine server
    * @param serverName
    * @param rbnbClientName
    * @param rbnbCacheSize
    * @param rbnbArchiveSize 
    */
    public ConnectionManager(String serverName,String rbnbClientName,int rbnbCacheSize, int rbnbArchiveSize) {
		//Logger.getLogger(Src.class.getName());
                this.serverName = serverName;
                this.rbnbClientName = rbnbClientName;
                this.rbnbCacheSize = rbnbCacheSize;
                this.rbnbArchiveSize = rbnbArchiveSize;
                initialised = 1;
		
	}
    /**
    * ConnectionManager
    * Sets up structure for connection to Dataturbine server
    * @param serverName
    * @param rbnbClientName
    * @param rbnbCacheSize
    * @param rbnbArchiveSize 
    */
    public ConnectionManager(String ip){
        //Logger.getLogger(Src.class.getName());
        this.serverName = ip;
        this.rbnbClientName = "Marine Source";
        this.rbnbCacheSize = 1024;
        this.rbnbArchiveSize = 1024;
        initialised = 1;
    }
    
    /**
     * Connect
     * Initiates connection to dataturbine server
     * @return Source source connected to server
     * @throws SAPIException
     * @throws IOException 
     */
    public Source connect() throws SAPIException, IOException{
        if (0 < rbnbArchiveSize) {
			sapiSrc = new Source (rbnbCacheSize, "append", rbnbArchiveSize);
		} else {
			sapiSrc = new Source(rbnbCacheSize, "none", 0);
		}
		//this.cmap = generateCmap();
		sapiSrc.OpenRBNBConnection(serverName, rbnbClientName);
		//logger.config("Set up connection to RBNB on " + serverName +
		//		" as source = " + rbnbClientName);
		//logger.config(" with RBNB Cache Size = " + rbnbCacheSize + " and RBNB Archive Size = " + rbnbArchiveSize);
		//sapiSrc.Register(cmap);
		//sapiSrc.Flush(cmap);
                
                return sapiSrc;
    }
    
    /**
     * Disconnect
     * Disconnects source from daturbine server
     */
    public void disconnect() {
		if(sapiSrc == null || initialised == 0) {
			return;
		}

		if (rbnbArchiveSize > 0) { // then close and keep the ring buffer
			sapiSrc.Detach();
		} else { // close and scrap the cache
			sapiSrc.CloseRBNBConnection();
		}
		//logger.config("Closed RBNB connection");
	}
    
    /**
     * isConnected
     * Queries whether the connection was created
     * @return connection status
     */
    public boolean isConnected(){
        return connected == 1;
    }


    
}
