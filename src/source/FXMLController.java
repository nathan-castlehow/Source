package source;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Natus
 */
public class FXMLController implements Initializable {
    
   // @FXML
   // private Label label;
   String ip = null;
   @FXML Rectangle connectionBlock;
   @FXML Label connectionLabel;
   @FXML static ScrollPane sensors;

    @FXML
    private void addHandler(ActionEvent event) {
        Dialog d = new Dialog();
        //Node content = content = FXMLLoader.load(getClass().getResource("check-dialog.fxml"));
        d.setResizable(false);
    
      
        
       try {
           
           Node root = FXMLLoader.load(getClass().getResource("FXML_NEW_SENSOR.fxml"));
           d.getDialogPane().setContent(root);
           d.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
           d.show();
           
       } catch (IOException ex) {
           Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       
    }
    
    @FXML
    private void connectionHandler(ActionEvent event) {
      
        Dialog<String> d = new TextInputDialog();
        d.setTitle("Create New Connection");
        d.setHeaderText("Enter valid server IP in format xxx.xxx.xxx.xxx:port");
        boolean success = false;
        
       /**final Button btOk = (Button) d.getDialogPane().lookupButton(ButtonType.C);
        btOk.addEventFilter(ActionEvent.ACTION, bEvent -> {    
            flag = false
        });**/
        
            ip = d.showAndWait().get();
            if(ipValidator(ip)){
                success = DataTurbineSource.createConnection(ip);
                if(!success){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Connection Failed");
                    a.showAndWait();
                }
            }else{
               Alert a = new Alert(Alert.AlertType.ERROR);
               a.setContentText("Invalid IP Entered");
               a.showAndWait();
            }
            if(success){
                connectionLabel.setText("Connected");
                connectionBlock.setFill(Color.GREEN);
                        
            }
     
       
    }
    
    private String getResult(String d){
        
        return d;
    }
    public static ScrollPane getSensorPane(){
        return sensors;
    }
    
     @FXML
    private void locationHandler(ActionEvent event) {
        DirectoryChooser d = new DirectoryChooser();
        d.setTitle("Choose directory for local storage");
        Stage s = new Stage();
        File f = d.showDialog(s);
        System.out.println(f.toString());
    
 }
        
       
    
    
    @FXML
    private void aboutHandler(ActionEvent event) {
        
       
    }
    
    @FXML
    private void JavaDocHandler(ActionEvent event) {
        
       
    }
    
    @FXML
    private void DocumentationHandler(ActionEvent event) {
        
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private boolean ipValidator(String ip){
        String regex = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}:\\d{1,4}";
        return Pattern.matches(regex,ip);
        
    }
    
   
}
