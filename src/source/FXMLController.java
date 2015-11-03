package source;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
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
   @FXML static ListView sensors;
   @FXML TableView sensorTable;
   @FXML TableColumn senName;
   @FXML TableColumn senPath;
   @FXML TableColumn append;
   @FXML TableColumn controls;
   static ObservableList data = FXCollections.observableArrayList();
   
   
   //static ListView<HBox> listView;

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
        //listView = new ListView<HBox>();
    //NewClass n = new NewClass("HELLO");
    //data.add(n);
    senName.setCellValueFactory(
    new PropertyValueFactory<Sensor,String>("nameProp")
    );
    System.out.println("Complete");
    senPath.setCellValueFactory(
        new PropertyValueFactory<Sensor,String>("pathProp")
    );
    System.out.println("Complete");
    append.setCellValueFactory(
         new PropertyValueFactory<Sensor,String>("appendProp")
        );
    System.out.println("Complete");
    sensorTable.setItems(data);
    System.out.println("Complete");
     
    
    //controls.setCellValueFactory(
      //  new PropertyValueFactory<InvoiceEntry,String>("price")
    //);
    
    }    
    
    private boolean ipValidator(String ip){
        String regex = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}:\\d{1,4}";
        return Pattern.matches(regex,ip);
        
    }
    
    public static void add(Sensor s){
            System.out.println(s.name);
            data.add(s);
    }
}
   

