/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Natus
 */
public class Sensor {
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
        
    }

    void setAppendMode(boolean pressed) {
        appendMode = pressed;
    }

    void connect() {
        DataTurbineSource.getSH().runSensor(this);
        //SourceReader s = new SourceReader(name,);
        
    }

    void setChannelValue(int channelValue) {
        this.channelNumber = channelValue;
    }

    void setTimeStampColumn(int columnNumber) {
        timeStampColumn = columnNumber;
    }
    SimpleStringProperty nameProperty(){
        System.out.println("HERE\n\n\n\n\n\n");
        return new SimpleStringProperty(name);
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
