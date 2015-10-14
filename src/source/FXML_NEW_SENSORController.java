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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    
    
    private File f = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Delimiter dr : Delimiter.values())
         {
             delimiterOptions.getItems().add(dr.name());
        }
    }    
    
    @FXML
    private void fileBrowser(ActionEvent e){
        FileChooser d = new FileChooser();
        d.setTitle("Choose data file");
        Stage s = new Stage();
        f = d.showOpenDialog(s);
        fileLabel.setText(f.toString());
        
    }
    @FXML
    private void inputHandler(ActionEvent e){
        
        Stage addWindow = (Stage) ((Node) (e.getSource())).getScene().getWindow();
        addWindow.close();
        Sensor s = new Sensor();
        s.setName(sensorName.getText());
        s.setDelimiter(delimiterOptions.getValue());
        s.setinfoLineNumber(Integer.parseInt(infoLineNumber.getText()));
        s.dataLineNumber(Integer.parseInt(dataLineNumber.getText()));
        s.setAppendMode(appendMode.isPressed());
        s.setFile(f);
        s.setTimeStampColumn(Integer.parseInt(timeStampColumn.getText()));
        s.connect();
       
    }
    
    
    
}
