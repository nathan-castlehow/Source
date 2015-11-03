/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Natus
 */
public class Sensor {
    private SimpleStringProperty nameProp;
    private SimpleStringProperty pathProp;
    private SimpleStringProperty appendProp;
    
    String name;
    Delimiter d;
    int infoLineNumber;
    int dataLineNumber;
    int timeStampColumn;
    File f;
    boolean appendMode;
    int channelNumber;
    
    
    Sensor(){
        name = null;
        d = Delimiter.COMMA;
        infoLineNumber = 0;
        dataLineNumber = 1;
        f = null;
        appendMode = false;
    }

    
    void setName(String text) {
        name = text;
        nameProp = new SimpleStringProperty(name);
        
    }
    
    public StringProperty namePropProperty() {
        return nameProp;
    }
    
    String getName(){
        System.out.println("HERE\n\n\n\n\n\n");
        return name;
    }
    
    public StringProperty pathPropProperty() {
        return pathProp;
    }
    
    public StringProperty appendPropProperty() {
        return appendProp;
    }

    

    void setDelimiter(Object value) {
        
    }

    void setinfoLineNumber(int text) {
        infoLineNumber = text;
        
    }

    void dataLineNumber(int text) {
        dataLineNumber = text;
    }

    void setFile(File f) {
       this.f = f;
       pathProp = new SimpleStringProperty(f.toString());
        
    }

    void setAppendMode(boolean pressed) {
        appendMode = pressed;
        appendProp = new SimpleStringProperty(Boolean.toString(appendMode));
    }

    void connect() {
        DataTurbineSource.getSH().runSensor(this);
    }

    void setChannelValue(int channelValue) {
        this.channelNumber = channelValue;
    }

    void setTimeStampColumn(int columnNumber) {
        timeStampColumn = columnNumber;
    }
    
   
    
    
    
    File getF(){
        return f;
    }
    
    String getAppendMode(){
        return Boolean.toString(appendMode);
    }
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name:" + name + "\n");
        sb.append("Delimiter:" + d + "\n");
        sb.append("TimeStampColumn:" + timeStampColumn + "\n");
        sb.append("InfoLineNumber:" + infoLineNumber + "\n");
        sb.append("DataLineNumber:" + dataLineNumber + "\n");
        sb.append("File:" + f.toString() + "\n");
        sb.append("AppendMode:" + appendMode + "\n");
        
        return sb.toString();
    }
   
}
