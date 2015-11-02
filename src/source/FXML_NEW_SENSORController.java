/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Natus
 */
public class FXML_NEW_SENSORController implements Initializable {
    @FXML private Label fileLabel;
    @FXML private TextArea sensorName;
    @FXML private ChoiceBox delimiterOptions;
    @FXML private TextArea infoLineNumber;
    @FXML private TextArea dataLineNumber;
    @FXML private TextArea timeStampColumn;
    @FXML private CheckBox appendMode;
    @FXML private Label fileDirLabel;
    
    
    private File f = null;
    private boolean selected = true;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Delimiter dr : Delimiter.values())
         {
             delimiterOptions.getItems().add(dr.name());
        }
        appendMode.setSelected(true);
    }    
    
    /**
     * fileBrowser
     * Creates fileBrowser/DirBrowser to select sensor file
     * @param e 
     */
    @FXML
    private void fileBrowser(ActionEvent e){
        Stage s = new Stage();
        if(selected){
            FileChooser d = new FileChooser();
            d.setTitle("Choose data file");
            f = d.showOpenDialog(s);
            String[] filePath = f.toString().split(File.separator);
            fileLabel.setText(filePath[filePath.length -1]);
        }
        else{
            DirectoryChooser d = new DirectoryChooser();
            d.setTitle("Choose Directory");
            File dir = d.showDialog(s);
            String[] filePath = dir.toString().split(File.separator);
            fileLabel.setText(filePath[filePath.length -1]);
        }
    }
    
    /**
     * inputHandler
     * @param e 
     */
    @FXML
    private void inputHandler(ActionEvent e){
        Sensor s = new Sensor();
        boolean valid = true;
        boolean s1 = valEmp(sensorName.getText()) || valEmp(delimiterOptions.getValue().toString());
        boolean s2 = valEmp(infoLineNumber.getText()) || valEmp(dataLineNumber.getText());
        boolean s3 = f == null || valEmp(timeStampColumn.getText());
        if( s1||s2||s3  ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please Complete All Fields");
            a.showAndWait();
        }else{
        
            s.setDelimiter(delimiterOptions.getValue());
            s.setName(sensorName.getText());
            s.setinfoLineNumber(Integer.parseInt(infoLineNumber.getText()));
            s.dataLineNumber(Integer.parseInt(dataLineNumber.getText()));
            s.setAppendMode(appendMode.isPressed());
            s.setFile(f);
            s.setTimeStampColumn(Integer.parseInt(timeStampColumn.getText()));
        
            Stage addWindow = (Stage) ((Node) (e.getSource())).getScene().getWindow();
            addWindow.close();
            s.connect();
            FXMLController.add(s);
            Stage primary = DataTurbineSource.getStage();
            
        }
        
        //HBox.
        //ScrollPane p;
        //p = FXMLController.getSensorPane();
        //Node n = p.getContent();
       
       
    }
    @FXML
    private void appendChange(ActionEvent e){
       // String NS ;
         if(appendMode.isSelected()){
           fileDirLabel.setText("File Path");
            selected = true;
        }else if(!appendMode.isSelected()){
             fileDirLabel.setText("Dir Path");
             selected = false;
        }
                
    }
    private boolean valEmp(String s){
    return s =="";
    
    }
    
}
