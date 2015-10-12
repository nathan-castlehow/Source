/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    @FXML private CheckBox appendMode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void fileBrowser(ActionEvent e){
        FileChooser d = new FileChooser();
        d.setTitle("Choose data file");
        Stage s = new Stage();
        File f = d.showOpenDialog(s);
        fileLabel.setText(f.toString());
        
    }
    @FXML
    private void inputHandler(){
        
    }
    
    
    
}
