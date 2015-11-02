package source;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.rbnb.sapi.SAPIException;
import com.rbnb.sapi.Source;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



/**
 *
 * @author Natus
 */
public class DataTurbineSource extends Application {
    //final static String sourceName = "Source1";
    static Source s = null;
    static SensorHandler SH;
    static Stage stage;
    static ConnectionManager c;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMinHeight(430);
        stage.setMinWidth(600);
        //stage.getIcons().add(new Image("file:Sea_Wave_PNG.png"));//set window icon
        stage.show();
    }
    static public Stage getStage(){
        return stage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // check directory for sensor files and load in
        
        launch(args);
        if(SH != null){
            SH.SHShutDown();
        }// gracefully shut down sensors on exit
        if(c != null){
            c.disconnect();
        }// gracefully disconnect from server
        
    }
    
/**
 * createConnection
 * static method to initialize Sensor Handler and create connection Handler
 * @param ip
 * @return 
 */
public static boolean createConnection(String ip){
    c = new ConnectionManager(ip);
        try {
            s = c.connect();
            SH = new SensorHandler(s);
            c.start();
            //try {
        } catch (SAPIException | IOException ex) {
            Logger.getLogger(DataTurbineSource.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        //} catch (SAPIException ex) {
          // // Logger.getLogger(DataTurbineSource.class.getName()).log(Level.SEVERE, null, ex);
           // return false;
        //} catch (IOException ex) {
          //  Logger.getLogger(DataTurbineSource.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
        //}
        return true;

}
public static SensorHandler getSH(){
    return SH;
}
    
}

/**
 * @param args the command line arguments
 *
 * public static void main(String[] args) { launch(args); }
 */
